package com.primeng.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Producto - Modelo de datos para representar un producto
 * 
 * Esta clase es un POJO (Plain Old Java Object) que representa
 * la entidad de negocio Producto. Se usa principalmente en los
 * ejemplos de DataTable y otros componentes de visualización de datos.
 * 
 * Anotaciones Lombok:
 * - @Data: Genera getters, setters, toString, equals y hashCode
 * - @AllArgsConstructor: Genera constructor con todos los argumentos
 * - @NoArgsConstructor: Genera constructor sin argumentos (requerido para JSF)
 * 
 * Buenas prácticas:
 * - Implementa Serializable para poder ser almacenado en sesión
 * - Usa tipos de datos apropiados (Double para precios, Date para fechas)
 * - Nombres de campos descriptivos y en español para claridad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** ID único del producto */
    private Long id;
    
    /** Código del producto (SKU) */
    private String codigo;
    
    /** Nombre del producto */
    private String nombre;
    
    /** Descripción detallada del producto */
    private String descripcion;
    
    /** Categoría a la que pertenece el producto */
    private String categoria;
    
    /** Precio del producto */
    private Double precio;
    
    /** Cantidad disponible en stock */
    private Integer stock;
    
    /** Fecha de registro del producto */
    private Date fechaRegistro;
    
    /** Estado del producto (Activo/Inactivo) */
    private String estado;
    
    /** URL de la imagen del producto */
    private String imagenUrl;
    
    /**
     * Constructor de conveniencia sin ID (para nuevos productos)
     * El ID se generará automáticamente al guardar
     */
    public Producto(String codigo, String nombre, String descripcion, 
                   String categoria, Double precio, Integer stock, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
        this.fechaRegistro = new Date();
    }
}
