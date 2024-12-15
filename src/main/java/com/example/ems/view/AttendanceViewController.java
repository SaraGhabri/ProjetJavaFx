/*package com.example.ems.view;

import com.example.ems.controller.AttendanceController;
import com.example.ems.model.Attendance;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AttendanceViewController {

    @FXML
    private TextField employeeIdField;

    @FXML
    private DatePicker dateField;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private TableView<Attendance> attendanceTable;

    @FXML
    private TableColumn<Attendance, Integer> attendanceIdColumn;

    @FXML
    private TableColumn<Attendance, Integer> employeeIdColumn;

    @FXML
    private TableColumn<Attendance, LocalDate> dateColumn;

    @FXML
    private TableColumn<Attendance, String> statusColumn;

    private final AttendanceController attendanceController;

    public AttendanceViewController() {
        this.attendanceController = new AttendanceController();
    }

    @FXML
    public void initialize() {
        attendanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusChoiceBox.setItems(FXCollections.observableArrayList("Present", "Excused", "Unexcused"));
    }

    @FXML
    public void markAttendance() {
        try {
            // Validate input fields
            if (employeeIdField.getText().isEmpty() || dateField.getValue() == null || statusChoiceBox.getValue() == null) {
                showAlert("Error", "Please fill all fields.");
                return;
            }

            int employeeId = Integer.parseInt(employeeIdField.getText());
            LocalDate date = dateField.getValue();
            String status = statusChoiceBox.getValue();

            // Ensure the status value is valid
            if (!status.equals("Present") && !status.equals("Excused") && !status.equals("Unexcused")) {
                showAlert("Error", "Invalid status value.");
                return;
            }

            Attendance attendance = new Attendance(0, employeeId, date, status);

            // Save attendance in the database
            attendanceController.markAttendance(attendance);

            // Reload attendance table
            loadAttendanceByEmployee(employeeId);

            showAlert("Success", "Attendance marked successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Employee ID must be a number.");
        } catch (SQLException e) {
            showAlert("Error", "Failed to mark attendance: " + e.getMessage());
        }
    }

    @FXML
    public void loadAttendanceByEmployee() {
        try {
            if (employeeIdField.getText().isEmpty()) {
                showAlert("Error", "Please enter an Employee ID.");
                return;
            }

            int employeeId = Integer.parseInt(employeeIdField.getText());
            loadAttendanceByEmployee(employeeId);
        } catch (NumberFormatException e) {
            showAlert("Error", "Employee ID must be a number.");
        } catch (SQLException e) {
            showAlert("Error", "Failed to load attendance: " + e.getMessage());
        }
    }

    private void loadAttendanceByEmployee(int employeeId) throws SQLException {
        List<Attendance> attendanceList = attendanceController.getAttendanceByEmployeeId(employeeId);
        attendanceTable.getItems().setAll(attendanceList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
*/
package com.example.ems.view;

import com.example.ems.controller.AttendanceController;
import com.example.ems.model.Attendance;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AttendanceViewController {

    @FXML
    private TextField employeeIdField;

    @FXML
    private DatePicker dateField;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private TableView<Attendance> attendanceTable;

    @FXML
    private TableColumn<Attendance, Integer> attendanceIdColumn;

    @FXML
    private TableColumn<Attendance, Integer> employeeIdColumn;

    @FXML
    private TableColumn<Attendance, LocalDate> dateColumn;

    @FXML
    private TableColumn<Attendance, String> statusColumn;

    @FXML
    private Button markAttendanceButton;

    @FXML
    private Button showHistoryButton;

    private final AttendanceController attendanceController;

    public AttendanceViewController() {
        this.attendanceController = new AttendanceController();
    }

    @FXML
    public void initialize() {
        attendanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusChoiceBox.setItems(FXCollections.observableArrayList("Present", "Excused", "Unexcused"));

        // Initialize buttons
        markAttendanceButton.setOnAction(event -> markAttendance());
        showHistoryButton.setOnAction(event -> showAttendanceHistory());
    }

    @FXML
    public void markAttendance() {
        try {
            // Validate input fields
            if (employeeIdField.getText().isEmpty() || dateField.getValue() == null || statusChoiceBox.getValue() == null) {
                showAlert("Error", "Please fill all fields.");
                return;
            }

            int employeeId = Integer.parseInt(employeeIdField.getText());
            LocalDate date = dateField.getValue();
            String status = statusChoiceBox.getValue();

            // Ensure the status value is valid
            if (!status.equals("Present") && !status.equals("Excused") && !status.equals("Unexcused")) {
                showAlert("Error", "Invalid status value.");
                return;
            }

            Attendance attendance = new Attendance(0, employeeId, date, status);

            // Save attendance in the database
            attendanceController.markAttendance(attendance);

            // Reload attendance table
            loadAttendanceByEmployee(employeeId);

            showAlert("Success", "Attendance marked successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Employee ID must be a number.");
        } catch (SQLException e) {
            showAlert("Error", "Failed to mark attendance: " + e.getMessage());
        }
    }

    @FXML
    public void showAttendanceHistory() {
        try {
            if (employeeIdField.getText().isEmpty()) {
                showAlert("Error", "Please enter an Employee ID.");
                return;
            }

            int employeeId = Integer.parseInt(employeeIdField.getText());
            loadAttendanceByEmployee(employeeId);

        } catch (NumberFormatException e) {
            showAlert("Error", "Employee ID must be a number.");
        } catch (SQLException e) {
            showAlert("Error", "Failed to load attendance: " + e.getMessage());
        }
    }

    private void loadAttendanceByEmployee(int employeeId) throws SQLException {
        List<Attendance> attendanceList = attendanceController.getAttendanceByEmployeeId(employeeId);
        attendanceTable.getItems().setAll(attendanceList);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
