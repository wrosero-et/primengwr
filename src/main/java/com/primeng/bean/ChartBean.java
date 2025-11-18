package com.primeng.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * ChartBean - Managed Bean para demostración de gráficos
 * 
 * Este bean gestiona los modelos de datos para diferentes tipos
 * de gráficos de PrimeFaces usando Chart.js:
 * - Gráfico de Línea
 * - Gráfico de Barras
 * - Gráfico Circular (Pie)
 * 
 * PrimeFaces integra Chart.js para proporcionar visualización
 * de datos interactiva y moderna.
 * 
 * Buenas prácticas:
 * - Separación de datos y presentación
 * - Modelos reutilizables
 * - Configuración centralizada
 */
@Named
@ViewScoped
@Data
@Slf4j
public class ChartBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ========== Modelos de Gráficos ==========
    
    /** Modelo para gráfico de líneas */
    private LineChartModel lineModel;
    
    /** Modelo para gráfico de barras */
    private BarChartModel barModel;
    
    /** Modelo para gráfico circular */
    private PieChartModel pieModel;
    
    // ========== Ciclo de Vida ==========
    
    /**
     * Inicialización del bean.
     * Crea los modelos de datos para cada gráfico.
     */
    @PostConstruct
    public void init() {
        log.info("Inicializando ChartBean");
        
        crearGraficoLineas();
        crearGraficoBarras();
        crearGraficoCircular();
        
        log.debug("ChartBean inicializado correctamente");
    }
    
    // ========== Métodos de Creación de Gráficos ==========
    
    /**
     * Crea el modelo para el gráfico de líneas.
     * Muestra evolución de ventas a lo largo del año.
     */
    private void crearGraficoLineas() {
        lineModel = new LineChartModel();
        
        // Serie de datos 2023
        LineChartSeries series2023 = new LineChartSeries();
        series2023.setLabel("Ventas 2023");
        series2023.set("Ene", 65);
        series2023.set("Feb", 59);
        series2023.set("Mar", 80);
        series2023.set("Abr", 81);
        series2023.set("May", 56);
        series2023.set("Jun", 55);
        series2023.set("Jul", 70);
        series2023.set("Ago", 75);
        series2023.set("Sep", 85);
        series2023.set("Oct", 90);
        series2023.set("Nov", 95);
        series2023.set("Dic", 100);
        
        // Serie de datos 2024
        LineChartSeries series2024 = new LineChartSeries();
        series2024.setLabel("Ventas 2024");
        series2024.set("Ene", 75);
        series2024.set("Feb", 69);
        series2024.set("Mar", 90);
        series2024.set("Abr", 91);
        series2024.set("May", 66);
        series2024.set("Jun", 65);
        series2024.set("Jul", 80);
        series2024.set("Ago", 85);
        series2024.set("Sep", 95);
        series2024.set("Oct", 100);
        series2024.set("Nov", 105);
        series2024.set("Dic", 110);
        
        lineModel.addSeries(series2023);
        lineModel.addSeries(series2024);
        
        // Configuración del gráfico
        lineModel.setTitle("Comparación de Ventas Anuales");
        lineModel.setLegendPosition("ne"); // Posición de leyenda: northeast
        lineModel.getAxis("y").setLabel("Ventas (miles)");
        lineModel.setShowPointLabels(false);
        lineModel.setStacked(false);
        
        log.debug("Gráfico de líneas creado");
    }
    
    /**
     * Crea el modelo para el gráfico de barras.
     * Muestra ventas por categoría de producto.
     */
    private void crearGraficoBarras() {
        barModel = new BarChartModel();
        
        // Serie de datos
        ChartSeries ventas = new ChartSeries();
        ventas.setLabel("Ventas por Categoría");
        ventas.set("Electrónica", 120);
        ventas.set("Ropa", 90);
        ventas.set("Alimentos", 75);
        ventas.set("Hogar", 60);
        ventas.set("Deportes", 85);
        
        barModel.addSeries(ventas);
        
        // Configuración del gráfico
        barModel.setTitle("Ventas por Categoría de Producto");
        barModel.setLegendPosition("ne");
        barModel.getAxis("y").setLabel("Ventas (miles)");
        barModel.setStacked(false);
        barModel.setShowPointLabels(true);
        
        log.debug("Gráfico de barras creado");
    }
    
    /**
     * Crea el modelo para el gráfico circular.
     * Muestra distribución de ventas por región.
     */
    private void crearGraficoCircular() {
        pieModel = new PieChartModel();
        
        // Datos del gráfico
        pieModel.set("América del Norte", 35);
        pieModel.set("América del Sur", 25);
        pieModel.set("Europa", 20);
        pieModel.set("Asia", 15);
        pieModel.set("África", 5);
        
        // Configuración del gráfico
        pieModel.setTitle("Distribución de Ventas por Región");
        pieModel.setLegendPosition("e"); // Posición de leyenda: east
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(300);
        
        log.debug("Gráfico circular creado");
    }
}
