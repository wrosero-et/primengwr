package com.primeng.bean;

import com.primeng.model.Producto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DataTableBean - Managed Bean para demostración de DataTable
 * 
 * Este bean gestiona una lista de productos y proporciona funcionalidad
 * para demostrar las capacidades del componente DataTable de PrimeFaces:
 * - Paginación
 * - Ordenamiento
 * - Filtrado
 * - Selección
 * - CRUD operations
 * 
 * Anotaciones:
 * - @Named: Hace el bean accesible desde JSF
 * - @ViewScoped: Mantiene el estado durante la vida de la vista
 * - @Data: Lombok genera getters/setters
 * - @Slf4j: Proporciona logging
 * 
 * Buenas prácticas:
 * - Lazy loading para grandes conjuntos de datos (comentado para simplicidad)
 * - Métodos CRUD separados para mantenibilidad
 * - Validaciones antes de operaciones
 * - Logging de operaciones importantes
 */
@Named
@ViewScoped
@Data
@Slf4j
public class DataTableBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ========== Propiedades ==========
    
    /** Lista de productos a mostrar en la tabla */
    private List<Producto> productos;
    
    /** Lista de productos filtrados */
    private List<Producto> productosFiltrados;
    
    /** Producto seleccionado en la tabla */
    private Producto productoSeleccionado;
    
    /** Lista de productos seleccionados (para selección múltiple) */
    private List<Producto> productosSeleccionados;
    
    /** Producto para edición/creación en diálogo */
    private Producto producto;
    
    /** Categorías disponibles para filtrado */
    private List<String> categorias;
    
    // ========== Ciclo de Vida ==========
    
    /**
     * Inicialización del bean.
     * Carga los datos de ejemplo.
     */
    @PostConstruct
    public void init() {
        log.info("Inicializando DataTableBean");
        
        productos = new ArrayList<>();
        productosSeleccionados = new ArrayList<>();
        
        // Cargar datos de ejemplo
        cargarProductosEjemplo();
        
        // Inicializar categorías para filtros
        inicializarCategorias();
        
        log.debug("DataTableBean inicializado con {} productos", productos.size());
    }
    
    // ========== Métodos de Negocio ==========
    
    /**
     * Prepara un nuevo producto para creación.
     */
    public void prepararNuevoProducto() {
        log.info("Preparando nuevo producto");
        this.producto = new Producto();
        this.producto.setFechaRegistro(new Date());
        this.producto.setEstado("Activo");
    }
    
    /**
     * Prepara un producto existente para edición.
     */
    public void prepararEdicionProducto() {
        log.info("Preparando edición de producto: {}", productoSeleccionado.getId());
        // Crear una copia del producto para edición
        this.producto = new Producto(
            productoSeleccionado.getId(),
            productoSeleccionado.getCodigo(),
            productoSeleccionado.getNombre(),
            productoSeleccionado.getDescripcion(),
            productoSeleccionado.getCategoria(),
            productoSeleccionado.getPrecio(),
            productoSeleccionado.getStock(),
            productoSeleccionado.getFechaRegistro(),
            productoSeleccionado.getEstado(),
            productoSeleccionado.getImagenUrl()
        );
    }
    
    /**
     * Guarda un producto (nuevo o editado).
     */
    public void guardarProducto() {
        log.info("Guardando producto: {}", producto.getNombre());
        
        try {
            if (producto.getId() == null) {
                // Nuevo producto
                producto.setId(generarNuevoId());
                productos.add(producto);
                
                mostrarMensaje(FacesMessage.SEVERITY_INFO, 
                    "Producto Creado", 
                    "El producto ha sido creado exitosamente");
                    
                log.info("Producto creado con ID: {}", producto.getId());
            } else {
                // Editar producto existente
                int index = productos.indexOf(productoSeleccionado);
                productos.set(index, producto);
                
                mostrarMensaje(FacesMessage.SEVERITY_INFO, 
                    "Producto Actualizado", 
                    "El producto ha sido actualizado exitosamente");
                    
                log.info("Producto actualizado: {}", producto.getId());
            }
            
            // Limpiar selección
            productoSeleccionado = null;
            producto = null;
            
        } catch (Exception e) {
            log.error("Error al guardar producto", e);
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "No se pudo guardar el producto: " + e.getMessage());
        }
    }
    
    /**
     * Elimina el producto seleccionado.
     */
    public void eliminarProducto() {
        log.info("Eliminando producto: {}", productoSeleccionado.getId());
        
        try {
            productos.remove(productoSeleccionado);
            
            mostrarMensaje(FacesMessage.SEVERITY_INFO, 
                "Producto Eliminado", 
                "El producto ha sido eliminado exitosamente");
                
            log.info("Producto eliminado: {}", productoSeleccionado.getId());
            productoSeleccionado = null;
            
        } catch (Exception e) {
            log.error("Error al eliminar producto", e);
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "No se pudo eliminar el producto: " + e.getMessage());
        }
    }
    
    /**
     * Elimina múltiples productos seleccionados.
     */
    public void eliminarProductosSeleccionados() {
        log.info("Eliminando {} productos seleccionados", productosSeleccionados.size());
        
        try {
            productos.removeAll(productosSeleccionados);
            
            mostrarMensaje(FacesMessage.SEVERITY_INFO, 
                "Productos Eliminados", 
                productosSeleccionados.size() + " productos eliminados exitosamente");
                
            log.info("Productos eliminados: {}", productosSeleccionados.size());
            productosSeleccionados.clear();
            
        } catch (Exception e) {
            log.error("Error al eliminar productos", e);
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, 
                "Error", 
                "No se pudieron eliminar los productos: " + e.getMessage());
        }
    }
    
    /**
     * Exporta los datos filtrados.
     * Este método puede ser extendido para exportar a diferentes formatos.
     */
    public void exportarDatos() {
        log.info("Exportando datos de productos");
        
        List<Producto> datosExportar = productosFiltrados != null ? 
            productosFiltrados : productos;
            
        log.info("Exportando {} productos", datosExportar.size());
        
        // La exportación se maneja automáticamente por PrimeFaces
        // con el componente p:dataExporter
    }
    
    // ========== Métodos de Utilidad ==========
    
    /**
     * Genera un nuevo ID para productos.
     * En una aplicación real, esto lo manejaría la base de datos.
     */
    private Long generarNuevoId() {
        return productos.stream()
            .mapToLong(Producto::getId)
            .max()
            .orElse(0L) + 1;
    }
    
    /**
     * Muestra un mensaje FacesMessage al usuario.
     */
    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, summary, detail));
    }
    
    /**
     * Inicializa las categorías disponibles.
     */
    private void inicializarCategorias() {
        categorias = new ArrayList<>();
        categorias.add("Electrónica");
        categorias.add("Ropa");
        categorias.add("Alimentos");
        categorias.add("Hogar");
        categorias.add("Deportes");
    }
    
    /**
     * Carga productos de ejemplo para la demostración.
     * En una aplicación real, estos datos vendrían de una base de datos.
     */
    private void cargarProductosEjemplo() {
        productos.add(new Producto(1L, "PROD-001", "Laptop Dell XPS 13", 
            "Laptop ultradelgada con procesador Intel i7", "Electrónica", 
            1299.99, 15, new Date(), "Activo", null));
            
        productos.add(new Producto(2L, "PROD-002", "iPhone 14 Pro", 
            "Smartphone con cámara de 48MP", "Electrónica", 
            999.99, 25, new Date(), "Activo", null));
            
        productos.add(new Producto(3L, "PROD-003", "Camiseta Nike", 
            "Camiseta deportiva de algodón", "Ropa", 
            29.99, 100, new Date(), "Activo", null));
            
        productos.add(new Producto(4L, "PROD-004", "Zapatillas Adidas", 
            "Zapatillas running ultraboost", "Deportes", 
            149.99, 50, new Date(), "Activo", null));
            
        productos.add(new Producto(5L, "PROD-005", "Café Gourmet", 
            "Café premium de Colombia", "Alimentos", 
            12.99, 200, new Date(), "Activo", null));
            
        productos.add(new Producto(6L, "PROD-006", "Silla Ergonómica", 
            "Silla de oficina con soporte lumbar", "Hogar", 
            299.99, 30, new Date(), "Activo", null));
            
        productos.add(new Producto(7L, "PROD-007", "Monitor Samsung 27\"", 
            "Monitor 4K UHD", "Electrónica", 
            399.99, 20, new Date(), "Activo", null));
            
        productos.add(new Producto(8L, "PROD-008", "Mochila North Face", 
            "Mochila para senderismo 40L", "Deportes", 
            89.99, 45, new Date(), "Activo", null));
            
        productos.add(new Producto(9L, "PROD-009", "Cafetera Nespresso", 
            "Cafetera de cápsulas automática", "Hogar", 
            179.99, 35, new Date(), "Activo", null));
            
        productos.add(new Producto(10L, "PROD-010", "Auriculares Sony", 
            "Auriculares inalámbricos con cancelación de ruido", "Electrónica", 
            249.99, 40, new Date(), "Activo", null));
    }
}
