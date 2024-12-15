package com.example.ems.view;

import com.example.ems.controller.DepartmentController;
import com.example.ems.model.Department;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class DepartmentViewController {

    @FXML
    private TextField nameField;

    @FXML
    private Button addButton, editButton, deleteButton;

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, Integer> idColumn;

    @FXML
    private TableColumn<Department, String> nameColumn;

    private final DepartmentController departmentController;

    public DepartmentViewController() {
        this.departmentController = new DepartmentController();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Add selection listener to TableView for editing
        departmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Populate fields when a department is selected
                nameField.setText(newValue.getName());
            }
        });

        try {
            loadDepartmentData();
        } catch (SQLException e) {
            showAlert("Error", "Failed to load department data.");
        }
    }

    @FXML
    public void addDepartment() {
        try {
            String name = nameField.getText();
            if (name.isEmpty()) {
                showAlert("Error", "Department name cannot be empty.");
                return;
            }

            Department newDepartment = new Department(0, name);
            departmentController.addDepartment(newDepartment);

            loadDepartmentData();
        } catch (Exception e) {
            showAlert("Error", "Failed to add department.");
        }
    }

    @FXML
    public void editDepartment() {
        try {
            Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    showAlert("Error", "Department name cannot be empty.");
                    return;
                }

                selectedDepartment.setName(name);
                departmentController.editDepartment(selectedDepartment);
                loadDepartmentData();
            } else {
                showAlert("Error", "No department selected.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to edit department.");
        }
    }

    @FXML
    public void deleteDepartment() {
        try {
            Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                departmentController.deleteDepartment(selectedDepartment.getId());
                loadDepartmentData();
            } else {
                showAlert("Error", "No department selected.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete department.");
        }
    }

    private void loadDepartmentData() throws SQLException {
        List<Department> departments = departmentController.getAllDepartments();
        departmentTable.getItems().setAll(departments);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
