<p align="center">
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring Boot" width="200" height="55"/>
</p>

<h1 align="center">🚀 Proyecto Spring Boot – Enviart</h1>

<p align="center">
  Aplicación web desarrollada con <b>Spring Boot</b> para la gestión de acceso de usuarios y administración de envíos de mercancía.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen?logo=springboot" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-17-orange?logo=coffeescript" alt="Java"/>
  <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License"/>
  <img src="https://img.shields.io/badge/Build-Maven-red?logo=apachemaven" alt="Maven"/>
</p>

---

## 🧱 Tecnologías utilizadas

<p align="center">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="55" height="55"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring Boot" width="55" height="55"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="MySQL" width="55" height="55"/>
  <img src="https://cdn.worldvectorlogo.com/logos/tailwindcss.svg" alt="TailwindCSS" width="55" height="55"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" alt="HTML5" width="55" height="55"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/thymeleaf/thymeleaf-original.svg" alt="Thymeleaf" width="55" height="55"/>
</p>

---

## ⚙️ Requisitos previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- [Java JDK 17 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.8+](https://maven.apache.org/)
- [MySQL o MariaDB](https://www.mysql.com/)
- [Git](https://git-scm.com/)
- Un IDE como **IntelliJ IDEA**, **Eclipse** o **VS Code**

---

## 🔧 Instalación y configuración

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/villamiljuan14/Enviart_spring_boot-java.git
   cd Enviart_spring_boot-java
   ```

2. **Configurar la base de datos:**

   Edita el archivo `src/main/resources/application.properties` o `application.yml`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/enviart_db
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. **Instalar dependencias y compilar:**

   ```bash
   mvn clean install
   ```

4. **Ejecutar el proyecto:**

   ```bash
   mvn spring-boot:run
   ```

   o ejecuta directamente el archivo principal:

   ```bash
   src/main/java/com/proyecto/AccesoUsuarios/AccesoUsuariosApplication.java
   ```

---

## 🌐 Acceso a la aplicación

Una vez iniciado el servidor, abre tu navegador y entra a:

```
http://localhost:8080/
```

---

## 🔑 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/proyecto/AccesoUsuarios/
│   │   ├── controller/   # Controladores (lógica web)
│   │   ├── model/        # Entidades JPA
│   │   ├── repository/   # Repositorios (DAO)
│   │   ├── service/      # Lógica de negocio
│   │   └── AccesoUsuariosApplication.java
│   └── resources/
│       ├── static/       # Archivos CSS, JS, imágenes
│       ├── templates/    # Vistas Thymeleaf (.html)
│       └── application.properties
└── test/                 # Pruebas unitarias
```

---

## 🧠 Características principales

- Registro e inicio de sesión de usuarios.  
- Roles con **Spring Security** (ADMIN / USER).  
- Conexión a base de datos relacional.  
- Interfaz responsiva con **TailwindCSS**.  
- Plantillas dinámicas con **Thymeleaf**.  
- Integración con **JPA/Hibernate**.

---

## 🧪 Pruebas

Ejecuta los tests con:

```bash
mvn test
```

---

## 🧰 Scripts útiles

| Comando | Descripción |
|----------|-------------|
| `mvn clean` | Limpia el proyecto |
| `mvn install` | Compila y empaqueta la aplicación |
| `mvn spring-boot:run` | Ejecuta la aplicación |
| `mvn test` | Corre las pruebas unitarias |

---

## 🤝 Contribuciones

1. Haz un **fork** del repositorio.  
2. Crea una rama:  
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz commit:  
   ```bash
   git commit -m "Agregada nueva funcionalidad"
   ```
4. Sube tus cambios:  
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Abre un **Pull Request**.

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **MIT**.  
Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 👨‍💻 Autor

**Manuel Villamil**  
🔗 [GitHub](https://github.com/villamiljuan14)
