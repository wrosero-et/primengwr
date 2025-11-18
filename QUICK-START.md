# Guía Rápida de Inicio - PrimeNG JSF Showcase

## 🚀 Inicio Rápido (5 minutos)

### Requisitos Mínimos
- Java 11+ instalado
- Maven 3.6+ instalado
- 10 minutos de tu tiempo

### Opción 1: Ejecutar con Maven (Más Rápido)

1. **Clonar el repositorio:**
```bash
git clone https://github.com/wrosero-et/primengwr.git
cd primengwr
```

2. **Compilar y ejecutar:**
```bash
mvn clean package
```

3. **Desplegar en servidor:**
   - El archivo WAR estará en: `target/primeng-jsf-showcase.war`
   - Copia este archivo a la carpeta `webapps` de tu servidor (Tomcat, WildFly, etc.)

4. **Acceder a la aplicación:**
```
http://localhost:8080/primeng-jsf-showcase/
```

### Opción 2: Usar IDE (Desarrollo)

#### IntelliJ IDEA

1. **Importar proyecto:**
   - File → Open → Selecciona el directorio del proyecto
   - IntelliJ detectará automáticamente el proyecto Maven

2. **Configurar servidor:**
   - Run → Edit Configurations → + → Tomcat Server → Local
   - Configura la ruta a tu instalación de Tomcat
   - En "Deployment", agrega el artifact: `primeng-jsf-showcase:war exploded`

3. **Ejecutar:**
   - Clic en el botón "Run" (triángulo verde)
   - La aplicación se abrirá automáticamente en tu navegador

#### Eclipse

1. **Importar proyecto:**
   - File → Import → Maven → Existing Maven Projects
   - Selecciona el directorio del proyecto

2. **Configurar servidor:**
   - Window → Preferences → Server → Runtime Environments → Add
   - Selecciona Apache Tomcat y configura la ruta

3. **Ejecutar:**
   - Right-click en el proyecto → Run As → Run on Server
   - Selecciona tu servidor Tomcat

### Opción 3: Docker (Si tienes Docker instalado)

Crea un archivo `Dockerfile` en la raíz:

```dockerfile
FROM tomcat:9-jdk11
COPY target/primeng-jsf-showcase.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
```

Luego ejecuta:

```bash
mvn clean package
docker build -t primeng-jsf .
docker run -p 8080:8080 primeng-jsf
```

Accede en: `http://localhost:8080/primeng-jsf-showcase/`

## 📖 Primeros Pasos

### Explorar la Aplicación

1. **Página de Inicio** (`/index.xhtml`)
   - Vista general de la aplicación
   - Enlaces a ejemplos

2. **Formularios** (`/components/form.xhtml`)
   - Inputs variados
   - Validaciones
   - AJAX

3. **Tablas** (`/components/datatable.xhtml`)
   - CRUD completo
   - Filtros y ordenamiento
   - Exportación

4. **Gráficos** (`/components/charts.xhtml`)
   - Visualización de datos
   - Chart.js integrado

### Estructura del Código

```
src/main/
├── java/com/primeng/
│   ├── bean/          # Managed Beans (Controladores)
│   ├── model/         # Modelos de datos
│   ├── service/       # Lógica de negocio
│   └── util/          # Utilidades
├── resources/
│   ├── messages.properties  # i18n
│   └── logback.xml         # Logging
└── webapp/
    ├── WEB-INF/
    │   ├── templates/ # Templates Facelets
    │   ├── web.xml    # Configuración
    │   └── faces-config.xml
    ├── components/    # Páginas de ejemplo
    └── resources/     # CSS, JS, imágenes
```

## 🔧 Desarrollo

### Modificar Componentes

1. **Agregar un nuevo Managed Bean:**

```java
package com.primeng.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MiBean implements Serializable {
    private String valor;
    
    // Getters y setters
    public void metodoAccion() {
        // Lógica aquí
    }
}
```

2. **Crear una nueva página XHTML:**

```xml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets">

<ui:composition template="/WEB-INF/templates/main-template.xhtml">
    <ui:define name="title">Mi Página</ui:define>
    <ui:define name="content">
        <h:form>
            <p:inputText value="#{miBean.valor}"/>
            <p:commandButton value="Enviar" 
                           action="#{miBean.metodoAccion}"/>
        </h:form>
    </ui:define>
</ui:composition>
</html>
```

### Hot Reload (Desarrollo)

Para desarrollo más rápido:

1. **Configurar en `web.xml`:**
```xml
<context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Development</param-value>
</context-param>

<context-param>
    <param-name>javax.faces.FACELETS_REFRESH_PERIOD</param-name>
    <param-value>0</param-value>
</context-param>
```

2. **Usar JRebel o HotSwap Agent** para recarga automática de clases Java

## 🐛 Solución de Problemas Comunes

### Error: "Cannot find FacesContext"
**Solución:** Verifica que estés accediendo a `.xhtml`, no `.html`
```
✅ http://localhost:8080/primeng-jsf-showcase/index.xhtml
❌ http://localhost:8080/primeng-jsf-showcase/index.html
```

### Error: "Bean not found"
**Solución:** Asegúrate que `beans.xml` existe en `WEB-INF/`

### Componentes de PrimeFaces no se muestran
**Solución:** Verifica el namespace en el XHTML:
```xml
xmlns:p="http://primefaces.org/ui"
```

### Problemas de codificación
**Solución:** Asegúrate que todos los archivos estén en UTF-8

## 📚 Recursos de Aprendizaje

### Documentación Oficial
- [JSF 2.3 Spec](https://jakarta.ee/specifications/faces/2.3/)
- [PrimeFaces Showcase](https://www.primefaces.org/showcase/)
- [PrimeFlex](https://www.primefaces.org/primeflex/)

### Tutoriales Recomendados
- [Baeldung - JSF](https://www.baeldung.com/jsf)
- [Tutoriales de PrimeFaces](https://www.primefaces.org/docs/)

### Comunidad
- [Stack Overflow - JSF](https://stackoverflow.com/questions/tagged/jsf)
- [PrimeFaces Forum](https://github.com/primefaces/primefaces/discussions)

## 🎯 Próximos Pasos

1. **Personalizar el tema:**
   - Cambia el tema en `web.xml`
   - Modifica `custom.css`

2. **Agregar base de datos:**
   - Integra JPA/Hibernate
   - Crea DAOs y servicios

3. **Implementar seguridad:**
   - Agrega autenticación
   - Usa roles y permisos

4. **Optimizar para producción:**
   - Cambia `PROJECT_STAGE` a `Production`
   - Habilita compresión
   - Configura caché

## 💡 Tips de Productividad

1. **Usa snippets/templates en tu IDE** para crear beans y páginas rápidamente

2. **Aprovecha la documentación inline** - todos los archivos tienen comentarios explicativos

3. **Prueba en múltiples navegadores** - Chrome, Firefox, Safari, Edge

4. **Usa las herramientas de desarrollo del navegador** para debugging de AJAX

5. **Activa el logging en modo DEBUG** para ver qué está pasando:
```xml
<!-- En logback.xml -->
<logger name="com.primeng" level="DEBUG"/>
```

## 🤝 Contribuir

¿Encontraste un bug? ¿Tienes una mejora?
1. Crea un issue en GitHub
2. Haz un fork del proyecto
3. Envía un pull request

---

**¡Feliz codificación! 🚀**
