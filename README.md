# 📇 Nexus — Smart Contact Management System

> *A secure and intelligent contact management system to organize, manage, and interact with your contacts efficiently.*

**Nexus** is a full-stack web application built with Spring Boot, MySQL, and Thymeleaf that allows users to manage their contacts seamlessly. It follows a modular and scalable architecture with secure authentication and cloud integration.

---

## 📌 Project Overview

Nexus is a smart contact management platform where users can securely store, manage, and interact with their contacts. It supports features like authentication, cloud image storage, email integration, and data export.

Built using a server-side rendered approach, it connects to a relational database to ensure efficient data handling and persistence.

---

## ✨ Features

- 🔐 User Authentication — Secure login/signup with email verification and OAuth (Google, GitHub)
- 👤 Contact Management — Add, update, delete, and view contacts
- 🖼️ Image Upload — Store contact images using cloud services (AWS/Cloudinary)
- 🔍 Search & Pagination — Efficiently find contacts with pagination support
- 📧 Email Integration — Send emails directly from the application
- 📊 Export Data — Download contacts as Excel files
- 🔒 Spring Security — Role-based authentication and authorization

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java | Core programming language |
| Spring Boot | Backend framework |
| Spring MVC | Web architecture |
| Spring Data JPA | Database interaction |
| Spring Security | Authentication & authorization |
| MySQL / PostgreSQL | Relational database |
| Thymeleaf | Server-side templating |
| OAuth (Google, GitHub) | Social login |
| AWS / Cloudinary | Image storage |
| Java Mail API | Email services |
| Tailwind CSS / JS | Frontend styling |

---

## 📁 File Structure

```
Nexus/
│
├── src/main/java/      # Java source code
├── src/main/resources/
│   ├── templates/      # Thymeleaf templates
│   ├── static/         # CSS, JS, images
│   └── application.properties
├── uploads/            # Stored images (if local)
├── pom.xml             # Maven dependencies
└── README.md           # Documentation
```

---

## 🚀 Getting Started

### Prerequisites
- [Java](https://www.java.com/en/) (JDK 8 or above)
- [Maven](https://maven.apache.org/)
- [MySQL / PostgreSQL](https://www.postgresql.org/) 

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/SamruddhiNadgouda/Nexus.git
   cd Nexus
   ```
   
2. **Configure database**
   - Update application.properties with your DB credentials
  
3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Open in browser**
   ```
   http://localhost:8000
   ```

---

## 📦 Dependencies

```xml
<dependencies>
    <dependency>Spring Boot Starter Web</dependency>
    <dependency>Spring Boot Starter Data JPA</dependency>
    <dependency>Spring Boot Starter Security</dependency>
    <dependency>Thymeleaf</dependency>
    <dependency>MySQL Driver</dependency>
    <dependency>OAuth Client</dependency>
</dependencies>
```

---

## 🙋‍♀️ Author

**Samruddhi Nadgouda**  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin)](https://www.linkedin.com/in/samruddhi-nadgouda/)
[![GitHub](https://img.shields.io/badge/GitHub-black?logo=github)](https://github.com/SamruddhiNadgouda)
