package com.example.ems.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Attendance {
    private IntegerProperty attendance_id;
    private IntegerProperty id;
    private StringProperty status;
    private StringProperty date;

    public Attendance(int attendanceId, int employeeId, LocalDate date, String status) {
        this.attendance_id = new SimpleIntegerProperty(attendanceId);
        this.id = new SimpleIntegerProperty(employeeId);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleStringProperty(date.toString());  // Convert LocalDate to String
    }

    // Getter and setter methods for each field

    public IntegerProperty getAttendanceIdProperty() {
        return attendance_id;
    }

    public IntegerProperty getEmployeeIdProperty() {
        return id;
    }

    public StringProperty getStatusProperty() {
        return status;
    }

    public StringProperty getDateProperty() {
        return date;
    }

    // Getters for values if needed
    public int getAttendanceId() {
        return attendance_id.get();
    }

    public int getEmployeeId() {
        return id.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getDate() {
        return date.get();
    }

    // Setters for values
    public void setAttendanceId(int attendanceId) {
        this.attendance_id.set(attendanceId);
    }

    public void setEmployeeId(int employeeId) {
        this.id.set(employeeId);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
