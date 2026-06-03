-- ============================================================
-- CogniFlow EMS — MySQL Schema (Normalized to 3NF)
-- Run: mysql -u root -p < schema.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS ems_db;
USE ems_db;

-- Departments
CREATE TABLE departments (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    manager_id  INT DEFAULT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_dept_name (name)
);

-- Roles
CREATE TABLE roles (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    role_name    VARCHAR(50) NOT NULL UNIQUE,
    access_level TINYINT NOT NULL DEFAULT 1  -- 1=Employee, 2=Manager, 3=Admin
);

-- Employees
CREATE TABLE employees (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    phone       VARCHAR(15),
    salary      DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    dept_id     INT NOT NULL,
    role_id     INT NOT NULL,
    hire_date   DATE NOT NULL,
    is_active   BOOLEAN DEFAULT TRUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (dept_id) REFERENCES departments(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    INDEX idx_emp_email (email),
    INDEX idx_emp_dept  (dept_id),
    INDEX idx_emp_role  (role_id)
);

-- System Users (Login accounts)
CREATE TABLE users (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    employee_id   INT NOT NULL UNIQUE,
    role_id       INT NOT NULL,
    is_active     BOOLEAN DEFAULT TRUE,
    last_login    TIMESTAMP NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (role_id)     REFERENCES roles(id)
);

-- Role Permissions
CREATE TABLE role_permissions (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    role_id     INT NOT NULL,
    permission  VARCHAR(50) NOT NULL,   -- e.g. CREATE_EMPLOYEE, DELETE_EMPLOYEE
    is_allowed  BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (role_id) REFERENCES roles(id),
    UNIQUE KEY uq_role_perm (role_id, permission)
);

-- Audit Logs
CREATE TABLE audit_logs (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT NOT NULL,
    action      VARCHAR(20) NOT NULL,  -- CREATE, READ, UPDATE, DELETE
    table_name  VARCHAR(50) NOT NULL,
    record_id   INT,
    description TEXT,
    ip_address  VARCHAR(45),
    timestamp   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_audit_user  (user_id),
    INDEX idx_audit_time  (timestamp),
    INDEX idx_audit_table (table_name)
);

-- ── Seed Data ────────────────────────────────────────────
INSERT INTO roles (role_name, access_level) VALUES
    ('Admin',    3),
    ('Manager',  2),
    ('Employee', 1);

INSERT INTO departments (name) VALUES
    ('Engineering'), ('Human Resources'), ('Finance'), ('Marketing');

INSERT INTO role_permissions (role_id, permission, is_allowed) VALUES
    (3, 'CREATE_EMPLOYEE', TRUE),  (3, 'READ_EMPLOYEE', TRUE),
    (3, 'UPDATE_EMPLOYEE', TRUE),  (3, 'DELETE_EMPLOYEE', TRUE),
    (3, 'VIEW_REPORTS',    TRUE),  (3, 'MANAGE_USERS',    TRUE),
    (2, 'CREATE_EMPLOYEE', TRUE),  (2, 'READ_EMPLOYEE',   TRUE),
    (2, 'UPDATE_EMPLOYEE', TRUE),  (2, 'DELETE_EMPLOYEE', FALSE),
    (2, 'VIEW_REPORTS',    TRUE),  (2, 'MANAGE_USERS',    FALSE),
    (1, 'CREATE_EMPLOYEE', FALSE), (1, 'READ_EMPLOYEE',   TRUE),
    (1, 'UPDATE_EMPLOYEE', FALSE), (1, 'DELETE_EMPLOYEE', FALSE),
    (1, 'VIEW_REPORTS',    FALSE), (1, 'MANAGE_USERS',    FALSE);
