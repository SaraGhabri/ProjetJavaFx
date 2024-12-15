package com.example.ems.view;

import com.example.ems.controller.EmployeeController;
import com.example.ems.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class EmployeeViewController {

    @FXML
    private TextField nameField, emailField, positionField, departmentIdField;

    @FXML
    private Button addButton, editButton, deleteButton;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn, emailColumn, positionColumn;

    @FXML
    private TableColumn<Employee, Integer> departmentColumn;

    private final EmployeeController employeeController;

    public EmployeeViewController() {
        this.employeeController = new EmployeeController();
    }

    /*@FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
*/
    @FXML
    public void initialize() {
        // Set up table columns and data
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));

        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Populate fields with selected employee data
            if (newValue != null) {
                nameField.setText(newValue.getName());
                emailField.setText(newValue.getEmail());
                positionField.setText(newValue.getPosition());
                departmentIdField.setText(String.valueOf(newValue.getDepartmentId()));
            }
        });
        try {
            loadEmployeeData();
        } catch (SQLException e) {
            showAlert("Error", "Failed to load employee data.");
        }
    }

    /*@FXML
    public void addEmployee() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String position = positionField.getText();
            int departmentId = Integer.parseInt(departmentIdField.getText());

            Employee newEmployee = new Employee(0, name, email, position, departmentId);
            employeeController.addEmployee(newEmployee);

            loadEmployeeData();
        } catch (Exception e) {
            showAlert("Error", "Failed to add employee. Check input fields.");
        }
    }
*/
    @FXML
    public void addEmployee() {
        try {
            if (validateInputs()) {
                String name = nameField.getText();
                String email = emailField.getText();
                String position = positionField.getText();
                int departmentId = Integer.parseInt(departmentIdField.getText());

                Employee newEmployee = new Employee(0, name, email, position, departmentId);
                employeeController.addEmployee(newEmployee);

                loadEmployeeData();
                clearFormFields();
            } else {
                showAlert("Validation Error", "All fields must be filled correctly.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input for Department ID.");
        } catch (SQLException e) {
            showAlert("Error", "Failed to add employee: " + e.getMessage());
        }
    }

    private boolean validateInputs() {
        return !nameField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !positionField.getText().isEmpty() &&
                !departmentIdField.getText().isEmpty();
    }

    private void clearFormFields() {
        nameField.clear();
        emailField.clear();
        positionField.clear();
        departmentIdField.clear();
    }

/*    @FXML
    public void editEmployee() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                String name = nameField.getText();
                String email = emailField.getText();
                String position = positionField.getText();
                int departmentId = Integer.parseInt(departmentIdField.getText());

                selectedEmployee.setName(name);
                selectedEmployee.setEmail(email);
                selectedEmployee.setPosition(position);
                selectedEmployee.setDepartmentId(departmentId);

                employeeController.editEmployee(selectedEmployee);
                loadEmployeeData();
            } else {
                showAlert("Error", "No employee selected.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to edit employee.");
        }
    }*/
/*@FXML
public void editEmployee() {
    try {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            System.out.println("Editing Employee ID: " + selectedEmployee.getId()); // Debug: Print selected ID

            String name = nameField.getText();
            String email = emailField.getText();
            String position = positionField.getText();
            int departmentId = Integer.parseInt(departmentIdField.getText());

            selectedEmployee.setName(name);
            selectedEmployee.setEmail(email);
            selectedEmployee.setPosition(position);
            selectedEmployee.setDepartmentId(departmentId);

            employeeController.editEmployee(selectedEmployee);
            loadEmployeeData();
            showAlert("Success", "Employee updated successfully.");
        } else {
            showAlert("Error", "No employee selected.");
        }
    } catch (Exception e) {
        showAlert("Error", "Failed to edit employee: " + e.getMessage());
        e.printStackTrace(); // Print stack trace for debugging
    }
}*/
@FXML
public void editEmployee() {
    try {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            // Get values from fields
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String position = positionField.getText().trim();
            String departmentIdText = departmentIdField.getText().trim();

            // Validate that fields are not empty and departmentId is an integer
            if (name.isEmpty() || email.isEmpty() || position.isEmpty() || departmentIdText.isEmpty()) {
                showAlert("Error", "All fields must be filled in.");
                return;
            }

            int departmentId = Integer.parseInt(departmentIdText); // Parse department ID

            // Update the selected employee
            selectedEmployee.setName(name);
            selectedEmployee.setEmail(email);
            selectedEmployee.setPosition(position);
            selectedEmployee.setDepartmentId(departmentId);

            // Call controller to update employee in database
            employeeController.editEmployee(selectedEmployee);
            loadEmployeeData();
            showAlert("Success", "Employee updated successfully.");
        } else {
            showAlert("Error", "No employee selected.");
        }
    } catch (Exception e) {
        showAlert("Error", "Failed to edit employee: " + e.getMessage());
    }
}



    @FXML
    public void deleteEmployee() {
        try {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                employeeController.deleteEmployee(selectedEmployee.getId());
                loadEmployeeData();
            } else {
                showAlert("Error", "No employee selected.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete employee.");
        }
    }

    private void loadEmployeeData() throws SQLException {
        List<Employee> employees = employeeController.getAllEmployees();
        employeeTable.getItems().setAll(employees);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
