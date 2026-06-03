package com.ems.dao;

import com.ems.model.Employee;
import com.ems.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for Employee CRUD operations.
 * Uses JDBC PreparedStatements to prevent SQL injection.
 */
public class EmployeeDAO {

    public int create(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, email, phone, " +
                     "salary, dept_id, role_id, hire_date) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setBigDecimal(5, e.getSalary());
            ps.setInt(6, e.getDeptId());
            ps.setInt(7, e.getRoleId());
            ps.setDate(8, Date.valueOf(e.getHireDate()));
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        }
        return -1;
    }

    public Optional<Employee> findById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ? AND is_active = TRUE";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(mapRow(rs));
        }
        return Optional.empty();
    }

    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE is_active = TRUE ORDER BY last_name";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Employee> findByDepartment(int deptId) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE dept_id = ? AND is_active = TRUE";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, " +
                     "phone=?, salary=?, dept_id=?, role_id=? WHERE id=?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setBigDecimal(5, e.getSalary());
            ps.setInt(6, e.getDeptId());
            ps.setInt(7, e.getRoleId());
            ps.setInt(8, e.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean softDelete(int id) throws SQLException {
        String sql = "UPDATE employees SET is_active = FALSE WHERE id = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setEmail(rs.getString("email"));
        e.setPhone(rs.getString("phone"));
        e.setSalary(rs.getBigDecimal("salary"));
        e.setDeptId(rs.getInt("dept_id"));
        e.setRoleId(rs.getInt("role_id"));
        e.setHireDate(rs.getDate("hire_date").toLocalDate());
        e.setActive(rs.getBoolean("is_active"));
        return e;
    }
}
