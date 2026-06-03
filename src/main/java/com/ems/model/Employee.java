package com.ems.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private int deptId;
    private int roleId;
    private LocalDate hireDate;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Employee() {}

    public Employee(String firstName, String lastName, String email,
                    String phone, BigDecimal salary, int deptId,
                    int roleId, LocalDate hireDate) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.phone     = phone;
        this.salary    = salary;
        this.deptId    = deptId;
        this.roleId    = roleId;
        this.hireDate  = hireDate;
        this.isActive  = true;
    }

    public String getFullName() { return firstName + " " + lastName; }

    // Getters
    public int getId()              { return id; }
    public String getFirstName()    { return firstName; }
    public String getLastName()     { return lastName; }
    public String getEmail()        { return email; }
    public String getPhone()        { return phone; }
    public BigDecimal getSalary()   { return salary; }
    public int getDeptId()          { return deptId; }
    public int getRoleId()          { return roleId; }
    public LocalDate getHireDate()  { return hireDate; }
    public boolean isActive()       { return isActive; }

    // Setters
    public void setId(int id)                   { this.id = id; }
    public void setFirstName(String firstName)  { this.firstName = firstName; }
    public void setLastName(String lastName)    { this.lastName = lastName; }
    public void setEmail(String email)          { this.email = email; }
    public void setPhone(String phone)          { this.phone = phone; }
    public void setSalary(BigDecimal salary)    { this.salary = salary; }
    public void setDeptId(int deptId)           { this.deptId = deptId; }
    public void setRoleId(int roleId)           { this.roleId = roleId; }
    public void setHireDate(LocalDate d)        { this.hireDate = d; }
    public void setActive(boolean active)       { this.isActive = active; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', email='%s', dept=%d, active=%b}",
                id, getFullName(), email, deptId, isActive);
    }
}
