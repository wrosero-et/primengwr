package com.primeng.bean;

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

/**
 * FormBean - Managed Bean para la demostración de formularios
 * 
 * Este bean gestiona los datos y la lógica de negocio para los ejemplos
 * de formularios. Implementa ViewScoped para mantener el estado durante
 * múltiples peticiones AJAX en la misma vista.
 * 
 * Anotaciones utilizadas:
 * - @Named: Hace que el bean sea accesible desde las vistas JSF
 * - @ViewScoped: El bean vive durante el ciclo de vida de la vista
 * - @Data: Lombok genera getters, setters, equals, hashCode y toString
 * - @Slf4j: Lombok proporciona un logger SLF4J
 * 
 * Buenas prácticas:
 * - Implementa Serializable para la serialización de sesión
 * - Usa @PostConstruct para inicialización después de la construcción
 * - Separa la lógica de negocio de la vista
 * - Logging para debugging y auditoría
 */
@Named
@ViewScoped
@Data
@Slf4j
public class FormBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ========== Campos del Formulario ==========
    
    /** Nombre del usuario */
    private String nombre;
    
    /** Email del usuario */
    private String email;
    
    /** Edad del usuario */
    private Integer edad;
    
    /** Fecha de nacimiento */
    private Date fechaNacimiento;
    
    /** País seleccionado */
    private String pais;
    
    /** Lista de países disponibles */
    private List<String> paises;
    
    /** Descripción o comentarios */
    private String descripcion;
    
    /** Género seleccionado */
    private String genero;
    
    /** Aceptación de términos y condiciones */
    private boolean aceptaTerminos;
    
    /** Intereses seleccionados (checkboxes múltiples) */
    private List<String> intereses;
    
    /** Lista de intereses disponibles */
    private List<String> listaIntereses;
    
    // ========== Métodos de Ciclo de Vida ==========
    
    /**
     * Inicialización del bean después de la construcción.
     * Se ejecuta una sola vez cuando se crea el bean.
     */
    @PostConstruct
    public void init() {
        log.info("Inicializando FormBean");
        
        // Inicializar listas de opciones
        inicializarPaises();
        inicializarIntereses();
        
        // Valores por defecto (opcional)
        this.intereses = new ArrayList<>();
        
        log.debug("FormBean inicializado correctamente");
    }
    
    // ========== Métodos de Negocio ==========
    
    /**
     * Procesa el envío del formulario.
     * Valida los datos y muestra un mensaje de confirmación.
     */
    public void enviarFormulario() {
        log.info("Procesando envío de formulario para: {}", nombre);
        
        try {
            // Validaciones adicionales de negocio
            if (!validarDatos()) {
                return;
            }
            
            // Aquí iría la lógica de guardado en base de datos
            // Por ejemplo: usuarioService.guardar(usuario);
            
            // Simular procesamiento
            log.info("Formulario procesado correctamente");
            log.debug("Datos: nombre={}, email={}, país={}", nombre, email, pais);
            
            // Mostrar mensaje de éxito al usuario
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "¡Éxito!",
                "Formulario enviado correctamente. Bienvenido " + nombre + "!"
            );
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            // Limpiar formulario después del envío exitoso
            limpiarFormulario();
            
        } catch (Exception e) {
            log.error("Error al procesar formulario", e);
            
            // Mostrar mensaje de error al usuario
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Error",
                "Ocurrió un error al procesar el formulario: " + e.getMessage()
            );
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    /**
     * Valida los datos del formulario.
     * 
     * @return true si los datos son válidos, false en caso contrario
     */
    private boolean validarDatos() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        // Validación: Edad debe ser mayor de 18
        if (edad != null && edad < 18) {
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                "Advertencia",
                "Debes ser mayor de 18 años"
            );
            context.addMessage(null, message);
            return false;
        }
        
        // Validación: Debe aceptar términos
        if (!aceptaTerminos) {
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                "Advertencia",
                "Debes aceptar los términos y condiciones"
            );
            context.addMessage(null, message);
            return false;
        }
        
        return true;
    }
    
    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarFormulario() {
        log.info("Limpiando formulario");
        
        this.nombre = null;
        this.email = null;
        this.edad = null;
        this.fechaNacimiento = null;
        this.pais = null;
        this.descripcion = null;
        this.genero = null;
        this.aceptaTerminos = false;
        this.intereses = new ArrayList<>();
        
        log.debug("Formulario limpiado");
    }
    
    // ========== Métodos de Inicialización de Datos ==========
    
    /**
     * Inicializa la lista de países disponibles.
     */
    private void inicializarPaises() {
        paises = new ArrayList<>();
        paises.add("Argentina");
        paises.add("Brasil");
        paises.add("Chile");
        paises.add("Colombia");
        paises.add("Ecuador");
        paises.add("España");
        paises.add("México");
        paises.add("Perú");
        paises.add("Uruguay");
        paises.add("Venezuela");
    }
    
    /**
     * Inicializa la lista de intereses disponibles.
     */
    private void inicializarIntereses() {
        listaIntereses = new ArrayList<>();
        listaIntereses.add("Programación");
        listaIntereses.add("Diseño");
        listaIntereses.add("Base de Datos");
        listaIntereses.add("DevOps");
        listaIntereses.add("Seguridad");
        listaIntereses.add("Cloud Computing");
    }
    
    // ========== Métodos de Utilidad ==========
    
    /**
     * Método de ejemplo para AJAX.
     * Se ejecuta cuando cambia el país seleccionado.
     */
    public void onPaisChange() {
        log.info("País seleccionado: {}", pais);
        
        FacesMessage message = new FacesMessage(
            FacesMessage.SEVERITY_INFO,
            "País actualizado",
            "Has seleccionado: " + pais
        );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
