# PrimeNG JSF Showcase - Aplicación Web Java

## 📋 Descripción

Esta es una aplicación de demostración completa desarrollada con **JavaServer Faces (JSF)**, **PrimeFaces** y **PrimeFlex**, siguiendo las mejores prácticas de desarrollo Java Web. La aplicación muestra diversos componentes UI ricos e interactivos, implementando funcionalidades como formularios con validaciones, tablas de datos con CRUD completo, y diseño responsive.

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 11** - Lenguaje de programación
- **JSF 2.3 (Mojarra)** - Framework MVC para aplicaciones web Java
- **CDI (Weld)** - Contexts and Dependency Injection para gestión de beans
- **Maven 3.6+** - Gestión de dependencias y construcción del proyecto

### Frontend
- **PrimeFaces 13.0.5** - Librería de componentes UI ricos para JSF
- **PrimeFlex 3.x** - Sistema de grid CSS responsive
- **Font Awesome** - Biblioteca de iconos
- **Chart.js** - Visualización de datos (integrado con PrimeFaces)

### Herramientas y Utilidades
- **Lombok** - Reducción de código boilerplate
- **SLF4J + Logback** - Sistema de logging
- **OmniFaces** - Utilidades adicionales para JSF

## 📁 Estructura del Proyecto

```
primeng-jsf-showcase/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/primeng/
│   │   │       ├── bean/          # Managed Beans (controladores)
│   │   │       │   ├── FormBean.java
│   │   │       │   └── DataTableBean.java
│   │   │       ├── model/         # Modelos de datos (POJOs)
│   │   │       │   └── Producto.java
│   │   │       ├── service/       # Servicios de negocio
│   │   │       └── util/          # Clases utilitarias
│   │   ├── resources/
│   │   │   └── messages.properties # Internacionalización
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── templates/     # Templates Facelets
│   │       │   │   └── main-template.xhtml
│   │       │   ├── web.xml        # Configuración de la aplicación
│   │       │   ├── faces-config.xml
│   │       │   └── beans.xml      # Configuración CDI
│   │       ├── components/        # Páginas de componentes
│   │       │   ├── form.xhtml
│   │       │   └── datatable.xhtml
│   │       ├── error/             # Páginas de error
│   │       │   ├── 404.xhtml
│   │       │   └── 500.xhtml
│   │       ├── resources/         # Recursos estáticos
│   │       │   ├── css/
│   │       │   │   └── custom.css
│   │       │   └── js/
│   │       └── index.xhtml        # Página principal
│   └── test/
│       └── java/                  # Tests unitarios
└── pom.xml                        # Configuración Maven
```

## 🔧 Requisitos Previos

Para ejecutar esta aplicación necesitas:

1. **Java Development Kit (JDK) 11 o superior**
   ```bash
   java -version
   ```

2. **Apache Maven 3.6 o superior**
   ```bash
   mvn -version
   ```

3. **Servidor de Aplicaciones** (una de las siguientes opciones):
   - Apache Tomcat 9.0.x o superior (Recomendado)
   - WildFly / JBoss EAP
   - Payara Server
   - GlassFish

## 📥 Instalación y Configuración

### 1. Clonar o Descargar el Proyecto

```bash
git clone https://github.com/wrosero-et/primengwr.git
cd primengwr
```

### 2. Compilar el Proyecto con Maven

```bash
mvn clean install
```

Este comando:
- Descarga todas las dependencias necesarias
- Compila el código fuente Java
- Genera el archivo WAR en `target/primeng-jsf-showcase.war`

### 3. Opción A: Ejecutar con Maven (Tomcat Embebido)

La forma más rápida de ejecutar la aplicación es usar el plugin de Maven para Tomcat:

```bash
mvn clean package tomcat7:run
```

O agregar el plugin al `pom.xml` y ejecutar:

```bash
mvn clean package cargo:run
```

### 3. Opción B: Desplegar en Tomcat Local

1. **Descargar e instalar Apache Tomcat**:
   - Descarga desde: https://tomcat.apache.org/download-90.cgi
   - Extrae en una carpeta (ej: `C:\apache-tomcat-9.0.x` o `/opt/tomcat`)

2. **Copiar el archivo WAR**:
   ```bash
   cp target/primeng-jsf-showcase.war /ruta/a/tomcat/webapps/
   ```

3. **Iniciar Tomcat**:
   
   En Windows:
   ```bash
   cd C:\apache-tomcat-9.0.x\bin
   startup.bat
   ```
   
   En Linux/Mac:
   ```bash
   cd /opt/tomcat/bin
   ./startup.sh
   ```

4. **Acceder a la aplicación**:
   ```
   http://localhost:8080/primeng-jsf-showcase/
   ```

### 3. Opción C: Desplegar en WildFly

1. **Descargar WildFly**:
   - Descarga desde: https://www.wildfly.org/downloads/

2. **Iniciar WildFly**:
   ```bash
   cd wildfly-xx.x.x.Final/bin
   ./standalone.sh  # Linux/Mac
   standalone.bat   # Windows
   ```

3. **Desplegar la aplicación**:
   ```bash
   cp target/primeng-jsf-showcase.war wildfly-xx.x.x.Final/standalone/deployments/
   ```

4. **Acceder**:
   ```
   http://localhost:8080/primeng-jsf-showcase/
   ```

## 🎯 Funcionalidades Principales

### 1. Formularios Interactivos
- **Ubicación**: `/components/form.xhtml`
- Componentes de entrada variados (text, textarea, calendar, dropdown, radio, checkbox)
- Validaciones del lado del cliente y servidor
- AJAX integrado para interactividad sin recarga de página
- Contador de caracteres en textarea
- Mensajes de error específicos por campo

### 2. Tabla de Datos (DataTable)
- **Ubicación**: `/components/datatable.xhtml`
- Paginación configurable
- Ordenamiento por columnas
- Filtrado global y por columna
- Selección simple y múltiple
- Exportación a Excel, PDF y CSV
- CRUD completo (Crear, Leer, Actualizar, Eliminar)
- Diálogos modales para edición
- Confirmaciones antes de eliminar

### 3. Diseño Responsive
- Layout adaptable a móviles, tablets y desktop
- Uso de PrimeFlex Grid System
- Menú lateral colapsable en móviles
- Componentes que se adaptan automáticamente

## 🏗️ Arquitectura y Buenas Prácticas Implementadas

### 1. Arquitectura en Capas
```
Vista (XHTML) → Controlador (Managed Beans) → Modelo (POJOs) → Servicio → DAO
```

### 2. Managed Beans con CDI
- **@Named**: Hace los beans accesibles desde JSF
- **@ViewScoped**: Mantiene el estado durante la vida de la vista
- **@SessionScoped**: Para datos que persisten en la sesión
- **@ApplicationScoped**: Para datos compartidos en toda la aplicación

### 3. Inyección de Dependencias
```java
@Inject
private ProductoService productoService;
```

### 4. Validaciones
- **Bean Validation**: Anotaciones `@NotNull`, `@Size`, `@Email`, etc.
- **Validaciones JSF**: `<f:validateRegex>`, `<f:validateLength>`
- **Validaciones Personalizadas**: En los managed beans

### 5. Manejo de Errores
- Páginas de error personalizadas (404, 500)
- Logging con SLF4J
- Try-catch con mensajes amigables al usuario

### 6. Performance
- AJAX parcial para actualizar solo componentes necesarios
- `primefaces.MOVE_SCRIPTS_TO_BOTTOM=true` para mejor carga
- State saving en servidor para aplicaciones empresariales
- Lazy loading para grandes conjuntos de datos (preparado)

### 7. Seguridad
- Prevención de acceso directo a archivos XHTML
- Configuración de sesiones con cookies HTTP-only
- CSRF protection (integrado en JSF)
- Validaciones del lado del servidor (nunca confiar solo en cliente)

### 8. Código Limpio
- Comentarios explicativos en español
- Nombres descriptivos de variables y métodos
- Separación de responsabilidades
- Uso de Lombok para reducir boilerplate

## 📝 Configuración Importante

### Modo de Desarrollo vs Producción

En `web.xml`, cambiar el `PROJECT_STAGE`:

**Desarrollo** (más mensajes de error, sin caché):
```xml
<context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Development</param-value>
</context-param>
```

**Producción** (optimizado, con caché):
```xml
<context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Production</param-value>
</context-param>
```

### Cambiar el Tema de PrimeFaces

En `web.xml`:
```xml
<context-param>
    <param-name>primefaces.THEME</param-name>
    <param-value>saga</param-value>  <!-- Cambiar a: nova, vela, arya, etc. -->
</context-param>
```

Temas disponibles: https://www.primefaces.org/themes/

## 🔍 Logging

Los logs se pueden ver en:
- **Consola del servidor**: Salida estándar
- **Archivo de logs**: Configurar en `logback.xml`

Niveles de log configurables en las clases:
```java
log.debug("Mensaje de depuración");
log.info("Mensaje informativo");
log.warn("Advertencia");
log.error("Error", excepcion);
```

## 🧪 Testing

Para ejecutar los tests:
```bash
mvn test
```

Para generar reporte de cobertura:
```bash
mvn test jacoco:report
```

## 📚 Recursos Adicionales

### Documentación Oficial
- **JSF**: https://jakarta.ee/specifications/faces/
- **PrimeFaces**: https://www.primefaces.org/showcase/
- **PrimeFlex**: https://www.primefaces.org/primeflex/
- **CDI**: https://jakarta.ee/specifications/cdi/

### Tutoriales Recomendados
- PrimeFaces Showcase: https://www.primefaces.org/showcase/
- Baeldung JSF: https://www.baeldung.com/jsf
- OmniFaces: https://omnifaces.org/

## 🚧 Solución de Problemas Comunes

### Error: "Cannot find FacesContext"
- Asegúrate de que el FacesServlet esté correctamente configurado en `web.xml`
- Verifica que la URL incluya la extensión `.xhtml`

### Error: "Bean not found"
- Verifica que `beans.xml` existe en `WEB-INF/`
- Asegúrate de usar `@Named` en el managed bean
- Verifica el alcance del bean (@ViewScoped, @SessionScoped, etc.)

### Problemas de codificación (tildes, ñ)
- Verifica que los archivos estén en UTF-8
- El filtro de caracteres debe estar activo en `web.xml`

### PrimeFaces components no se renderizan
- Verifica que la dependencia de PrimeFaces esté en el classpath
- Comprueba que el namespace `xmlns:p="http://primefaces.org/ui"` esté declarado

## 🔄 Cambios y Mejoras Realizadas

### Cambios desde Angular PrimeNG a Java JSF con PrimeFaces

1. **Conversión de TypeScript a Java**:
   - Componentes Angular → Managed Beans CDI
   - Servicios → Servicios Java con inyección de dependencias
   - Modelos TypeScript → POJOs Java con Lombok

2. **Conversión de Templates**:
   - HTML + Angular directives → XHTML + Facelets
   - Angular bindings `[(ngModel)]` → JSF value bindings `#{bean.property}`
   - Angular events `(click)` → JSF actions `action="#{bean.method}"`

3. **Mejoras de Rendimiento**:
   - AJAX parcial en lugar de recarga completa
   - State saving en servidor
   - Lazy loading preparado para grandes datasets
   - Scripts al final del body
   - Cache de recursos estáticos

4. **Arquitectura**:
   - Patrón MVC con JSF
   - CDI para inyección de dependencias
   - Templates Facelets para reutilización
   - Separación clara de capas

## 📞 Soporte

Para preguntas o problemas:
- Crea un issue en el repositorio
- Consulta la documentación oficial de PrimeFaces
- Revisa los ejemplos en PrimeFaces Showcase

## 📄 Licencia

Este proyecto es una demostración educativa y está disponible bajo licencia MIT.

## ✨ Autor

Desarrollado como demostración de buenas prácticas en desarrollo Java Web con JSF, PrimeFaces y PrimeFlex.

---

**¡Disfruta desarrollando con JSF y PrimeFaces! 🚀**
