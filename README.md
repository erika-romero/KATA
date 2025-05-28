# Proyecto de Automatización de Pruebas con Selenium

## Descripción

Este proyecto implementa pruebas automatizadas para validar la funcionalidad del formulario web en el sitio [demoqa.com]([https://demoqa.com](https://demoqa.com/automation-practice-form)]).  
Utiliza Selenium WebDriver con Java y JUnit 5 para la automatización, junto con ExtentReports para la generación de reportes visuales.  

Las pruebas cubren casos como:  
- Validación de campos dependientes (por ejemplo, habilitación de dropdown de ciudad al seleccionar estado)  
- Validación de campos con datos inválidos (correo electrónico, teléfono)  
- Interacción con elementos dinámicos y dropdowns personalizados  
- Captura de pantallas en casos de éxito o error para evidencia  

## Requisitos

- Java JDK 11 o superior  
- Maven
- Navegador Chrome y ChromeDriver
- IDE recomendado: IntelliJ IDEA, Eclipse o Visual Studio Code  

## Instalación

1. Clonar este repositorio
2. Ejecutar las pruebas con el comando: mvn clean test
