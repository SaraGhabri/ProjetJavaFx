package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (name, email, position, department_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET name = ?, email = ?, position = ?, department_id = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE id = ?";
    private static final String GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM employee WHERE id = ?";
    private static final String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM employee";

    /*public void addEmployee(Employee employee) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getDepartmentId());
            ps.executeUpdate();
        }
    }*/
    public void addEmployee(Employee employee) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getDepartmentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            throw e; // Re-throw for handling in the view controller
        }
    }

    /*public void editEmployee(Employee employee) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, employee.getId());
            ps.executeUpdate();
        }
    }*/
    /*public void editEmployee(Employee employee) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, employee.getId()); // ID must match the WHERE clause

            int rowsUpdated = ps.executeUpdate(); // Execute and check affected rows

            if (rowsUpdated == 0) {
                throw new SQLException("Failed to edit employee: Employee ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            throw e;
        }
    }*/
    public void editEmployee(Employee employee) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, employee.getId()); // Ensure that the ID is included to update the correct record

            int rowsUpdated = ps.executeUpdate(); // Execute the query and check affected rows
            if (rowsUpdated == 0) {
                throw new SQLException("Failed to edit employee: Employee ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            throw e;
        }
    }




    public void deleteEmployee(int employeeId) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            ps.setInt(1, employeeId);
            ps.executeUpdate();
        }
    }

    public Employee getEmployeeById(int employeeId) throws SQLException {
        Employee employee = null;
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(GET_EMPLOYEE_BY_ID_SQL)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("position"), rs.getInt("department_id"));
            }
        }
        return employee;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DatabaseUtil.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_EMPLOYEES_SQL)) {
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("position"), rs.getInt("department_id"));
                employees.add(employee);
            }
        }
        return employees;
    }
}
