# 🏢 Employee Management System

> **Enterprise Database Application — Java · OOP · JDBC · MySQL · MVC Design Pattern**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://java.com)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://mysql.com)
[![Maven](https://img.shields.io/badge/Build-Maven-red.svg)](https://maven.apache.org)
[![Pattern](https://img.shields.io/badge/Pattern-MVC-green.svg)]()
[![Platform](https://img.shields.io/badge/Platform-Linux%20%7C%20Windows-lightgrey.svg)]()

A full-featured **Enterprise Employee Management System** built with Java and MySQL, following the **MVC Design Pattern**. Features role-based access control, complete CRUD operations, activity audit logging, and optimized database queries using JDBC prepared statements.

---

## ✨ Features

- 👥 **Full CRUD** — Create, Read, Update, Delete employees, departments & roles
- 🔐 **Role-Based Access Control (RBAC)** — Admin, Manager, Employee roles with permission levels
- 📋 **Activity Audit Logging** — Every action tracked with timestamp, user, and operation type
- ⚡ **JDBC Prepared Statements** — Prevents SQL injection, improves performance
- 🗄️ **Normalized MySQL Schema** — 3NF design with indexing & query optimization
- 🧱 **MVC Architecture** — Clean separation of Model, View, Controller layers
- 🐧 **Cross-platform** — Deployed and validated on Linux/Ubuntu & Windows
- 🛡️ **Robust Exception Handling** — Custom exceptions for all error scenarios

---

## 🏗️ Architecture (MVC)

```
┌──────────────────────────────────────────────┐
│                   VIEW LAYER                 │
│     ConsoleView · MenuView · ReportView      │
└────────────────────┬─────────────────────────┘
                     │
┌────────────────────▼─────────────────────────┐
│               CONTROLLER LAYER               │
│  EmployeeController · AuthController         │
│  DepartmentController · AuditController      │
└────────────────────┬─────────────────────────┘
                     │
┌────────────────────▼─────────────────────────┐
│               SERVICE LAYER                  │
│  EmployeeService · AuthService               │
│  DepartmentService · AuditService            │
└────────────────────┬─────────────────────────┘
                     │
┌────────────────────▼─────────────────────────┐
│                 DAO LAYER                    │
│  EmployeeDAO · UserDAO · DepartmentDAO       │
│  RoleDAO · AuditDAO  (JDBC + PreparedStmt)   │
└────────────────────┬─────────────────────────┘
                     │
┌────────────────────▼─────────────────────────┐
│              MySQL DATABASE                  │
│  employees · departments · roles · users     │
│  audit_logs · role_permissions               │
└──────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
Employee-Management-System/
├── src/main/java/com/ems/
│   ├── model/                  # Entity classes
│   │   ├── Employee.java
│   │   ├── Department.java
│   │   ├── Role.java
│   │   └── AuditLog.java
│   ├── dao/                    # Data Access Layer (JDBC)
│   │   ├── EmployeeDAO.java
│   │   ├── DepartmentDAO.java
│   │   ├── RoleDAO.java
│   │   └── AuditDAO.java
│   ├── service/                # Business Logic Layer
│   │   ├── EmployeeService.java
│   │   ├── AuthService.java
│   │   └── AuditService.java
│   ├── controller/             # MVC Controllers
│   │   ├── EmployeeController.java
│   │   ├── AuthController.java
│   │   └── DepartmentController.java
│   ├── view/                   # Console UI
│   │   ├── MainMenuView.java
│   │   ├── EmployeeView.java
│   │   └── ReportView.java
│   └── util/                   # Utilities
│       ├── DBConnection.java
│       ├── InputValidator.java
│       └── PasswordHasher.java
├── src/main/resources/
│   ├── db/
│   │   └── schema.sql          # Full normalized MySQL schema
│   └── config.properties       # DB credentials (excluded from git)
├── pom.xml                     # Maven build config
├── .gitignore
└── README.md
```

---

## 🗄️ Database Schema

```sql
-- Core tables
employees      (id, name, email, phone, salary, dept_id, role_id, created_at)
departments    (id, name, manager_id, created_at)
roles          (id, role_name, access_level)
users          (id, username, password_hash, employee_id, role_id, is_active)
audit_logs     (id, user_id, action, table_name, record_id, timestamp)
role_permissions (role_id, permission, is_allowed)
```

---

## ⚙️ Setup & Installation

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.8+
- Linux/Ubuntu or Windows

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/Karthikeyan8490/Employee-Management-System.git
cd Employee-Management-System

# 2. Create MySQL database
mysql -u root -p < src/main/resources/db/schema.sql

# 3. Configure database credentials
cp src/main/resources/config.properties.example src/main/resources/config.properties
# Edit config.properties with your DB credentials

# 4. Build with Maven
mvn clean compile

# 5. Run the application
mvn exec:java -Dexec.mainClass="com.ems.Main"
```

---

## 🔐 Role-Based Access Control

| Role     | Create | Read | Update | Delete | Reports | Admin |
|----------|--------|------|--------|--------|---------|-------|
| Admin    | ✅     | ✅   | ✅     | ✅     | ✅      | ✅    |
| Manager  | ✅     | ✅   | ✅     | ❌     | ✅      | ❌    |
| Employee | ❌     | ✅   | ❌     | ❌     | ❌      | ❌    |

---

## 🛠️ Tech Stack

| Layer      | Technology                    |
|------------|-------------------------------|
| Language   | Java 17                       |
| Database   | MySQL 8.0                     |
| Connectivity | JDBC + PreparedStatements   |
| Build Tool | Maven                         |
| Pattern    | MVC + DAO + Service Layer     |
| Platform   | Linux/Ubuntu, Windows         |
| Security   | BCrypt password hashing, RBAC |

---

## 👨‍💻 Author

**Bukka Karthikeyan** — 2451-22-733-150  
MVSR Engineering College, Hyderabad

---

## 📄 License

This project is licensed under the MIT License.
