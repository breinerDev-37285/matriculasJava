# matriculasJava

## Examen practico para la materia de aplicaciones informaticas II

Integrantes: 

  * Jose Pai
  * Erik Morocho
  * Erick Vasconez
  

### dependencias 

  Para ejecutar el proyecto se debe tener instalado los siguintes programas
  
  * Eclipse enterprise edition (IDE)
  * Base de datos Postgresql
  * Servidor Wildfly ( para la construcción de este proyecto se uso la version 18 )
  
### Modo de Uso

  * Asegurarse de tener activa la conexión en la base de datos, si el entorno de ejecución es linux, entonces usar los siguientes comandos
    - systemctl enable postgresql.service  - agrega un enlace simbolico para que el servicio inicie con el sistema, este comando es opcional
    - systemctl start postgresql.service  - inicia el servicio de postgresql
    - systemtl status postgresql.service  - verifica el estado del servicio
    
  * En la herramienta eclipse enterprise edition, agregar la conexión a la base de datos
    - En el panel de herramientas buscar la opcion servidor y escoger la opcion wildfly
    - agregar la ruta del servidor de wildfly
    
  * Configurar wildfly
    - se debe agregar la conexión de wildfly con la base de datos postgres en el archivo standalone.xml
    - en la ruta de instalación de wildfly dirigirse a la carpeta deployments y agregar el archivo de conexión de postgresql.
    
