package com.example.ems.controller;

import com.example.ems.model.Department;
import com.example.ems.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentController {

    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department (name) VALUES (?)";
    private static final String UPDATE_DEPARTMENT_SQL = "UPDATE department SET name = ? WHERE id = ?";
    private static final String DELETE_DEPARTMENT_SQL = "DELETE FROM department WHERE id = ?";
    private static final String GET_DEPARTMENT_BY_ID_SQL = "SELECT * FROM department WHERE id = ?";
    private static final String GET_ALL_DEPARTMENTS_SQL = "SELECT * FROM department";

    /*public void addDepartment(Department department) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.executeUpdate();
        }
    }

    public void editDepartment(Department department) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();
        }
    }*/
    /*public void addDepartment(Department department) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to add department", e);
        }
    }

    public void editDepartment(Department department) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to edit department", e);
        }
    }


    public void deleteDepartment(int departmentId) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(DELETE_DEPARTMENT_SQL)) {
            ps.setInt(1, departmentId);
            ps.executeUpdate();
        }
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        Department department = null;
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(GET_DEPARTMENT_BY_ID_SQL)) {
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                department = new Department(rs.getInt("id"), rs.getString("name"));
            }
        }
        return department;
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        try (Connection conn = DatabaseUtil.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_DEPARTMENTS_SQL)) {
            while (rs.next()) {
                Department department = new Department(rs.getInt("id"), rs.getString("name"));
                departments.add(department);
            }
        }
        return departments;
    }*/
    public void addDepartment(Department department) throws SQLException {
        String INSERT_DEPARTMENT_SQL = "INSERT INTO department (name) VALUES (?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add department: " + e.getMessage());
            throw new SQLException("Failed to add department", e);  // Throwing the exception to be handled in the controller
        }
    }

    public void editDepartment(Department department) throws SQLException {
        String UPDATE_DEPARTMENT_SQL = "UPDATE department SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_DEPARTMENT_SQL)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to edit department: " + e.getMessage());
            throw new SQLException("Failed to edit department", e);
        }
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        String DELETE_DEPARTMENT_SQL = "DELETE FROM department WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(DELETE_DEPARTMENT_SQL)) {
            ps.setInt(1, departmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete department: " + e.getMessage());
            throw new SQLException("Failed to delete department", e);
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String GET_ALL_DEPARTMENTS_SQL = "SELECT * FROM department";  // Ensure this is the correct query

        try (Connection conn = DatabaseUtil.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_DEPARTMENTS_SQL)) {
            while (rs.next()) {
                Department department = new Department(rs.getInt("id"), rs.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            System.err.println("Failed to load departments: " + e.getMessage());
            throw new SQLException("Failed to load departments", e);
        }
        return departments;
    }
}
