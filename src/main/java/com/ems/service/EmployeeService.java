package com.ems.service;

import com.ems.dao.AuditDAO;
import com.ems.dao.EmployeeDAO;
import com.ems.model.AuditLog;
import com.ems.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Business logic layer for Employee operations.
 * Validates input, calls DAO, and logs all actions to audit trail.
 */
public class EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final AuditDAO    auditDAO    = new AuditDAO();

    public Employee addEmployee(Employee emp, int actorUserId) throws SQLException {
        validateEmployee(emp);
        int newId = employeeDAO.create(emp);
        emp.setId(newId);
        auditDAO.log(new AuditLog(actorUserId, "CREATE", "employees",
                newId, "Created employee: " + emp.getFullName()));
        return emp;
    }

    public Optional<Employee> getEmployee(int id, int actorUserId) throws SQLException {
        auditDAO.log(new AuditLog(actorUserId, "READ", "employees", id, "Viewed employee ID=" + id));
        return employeeDAO.findById(id);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.findAll();
    }

    public boolean updateEmployee(Employee emp, int actorUserId) throws SQLException {
        validateEmployee(emp);
        boolean updated = employeeDAO.update(emp);
        if (updated) auditDAO.log(new AuditLog(actorUserId, "UPDATE", "employees",
                emp.getId(), "Updated employee: " + emp.getFullName()));
        return updated;
    }

    public boolean removeEmployee(int id, int actorUserId) throws SQLException {
        boolean deleted = employeeDAO.softDelete(id);
        if (deleted) auditDAO.log(new AuditLog(actorUserId, "DELETE", "employees",
                id, "Soft-deleted employee ID=" + id));
        return deleted;
    }

    private void validateEmployee(Employee e) {
        if (e.getFirstName() == null || e.getFirstName().isBlank())
            throw new IllegalArgumentException("First name is required");
        if (e.getEmail() == null || !e.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$"))
            throw new IllegalArgumentException("Invalid email format");
        if (e.getSalary() != null && e.getSalary().doubleValue() < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
    }
}
