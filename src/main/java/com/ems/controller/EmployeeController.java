package com.ems.controller;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/** MVC Controller — delegates to EmployeeService, returns data to View. */
public class EmployeeController {
    private final EmployeeService service = new EmployeeService();
    private int currentUserId = 1; // Set after login

    public void setCurrentUserId(int id) { this.currentUserId = id; }

    public Employee create(Employee emp) throws SQLException {
        return service.addEmployee(emp, currentUserId);
    }
    public Optional<Employee> getById(int id) throws SQLException {
        return service.getEmployee(id, currentUserId);
    }
    public List<Employee> getAll() throws SQLException {
        return service.getAllEmployees();
    }
    public boolean update(Employee emp) throws SQLException {
        return service.updateEmployee(emp, currentUserId);
    }
    public boolean delete(int id) throws SQLException {
        return service.removeEmployee(id, currentUserId);
    }
}
