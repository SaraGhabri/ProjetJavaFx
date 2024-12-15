package com.example.ems.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private Button employeesButton;

    @FXML
    private Button departmentsButton;

    @FXML
    private Button attendanceButton;

    @FXML
    private Button leaveButton;

    @FXML
    public void initialize() {
        // Event handlers for buttons
        employeesButton.setOnAction(event -> openEmployeesManagement());
        departmentsButton.setOnAction(event -> openDepartmentsManagement());
        attendanceButton.setOnAction(event -> openAttendanceManagement());
        leaveButton.setOnAction(event -> leaveApplication());
    }

    // Open the Employees Management page
    public void openEmployeesManagement() {
        try {
            Stage stage = new Stage();
            // Correct path to load the employee-view.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ems/employee-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Employee Management");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Open the Departments Management page
    public void openDepartmentsManagement() {
        try {
            Stage stage = new Stage();
            // Correct path to load the department-view.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ems/department-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Department Management");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Open the Attendance Management page
    public void openAttendanceManagement() {
        try {
            Stage stage = new Stage();
            // Correct path to load the attendance-view.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ems/attendance-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Attendance Management");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle the "Leave" button logic to close the application
    public void leaveApplication() {
        System.out.println("Exiting application...");
        Stage stage = (Stage) leaveButton.getScene().getWindow();
        stage.close();
    }
}
