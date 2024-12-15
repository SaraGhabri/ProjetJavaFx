package com.example.ems.controller;

import com.example.ems.model.Attendance;
import com.example.ems.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceController {

    private static final String INSERT_ATTENDANCE_SQL = "INSERT INTO attendance (id, date, status) VALUES (?, ?, ?)";
    private static final String GET_ATTENDANCE_BY_EMPLOYEE_SQL = "SELECT * FROM attendance WHERE id = ?";
    private static final String GET_UNEXCUSED_ABSENCES_SQL = "SELECT COUNT(*) FROM attendance WHERE id = ? AND status = 'Unexcused' AND date >= CURDATE() - INTERVAL 1 WEEK";

    public void markAttendance(Attendance attendance) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(INSERT_ATTENDANCE_SQL)) {
            ps.setInt(1, attendance.getEmployeeId());
            ps.setDate(2, Date.valueOf(attendance.getDate()));
            ps.setString(3, attendance.getStatus());
            ps.executeUpdate();

            if ("Unexcused".equals(attendance.getStatus())) {
                checkUnexcusedAbsences(attendance.getAttendanceId());
            }
        }
    }

    private void checkUnexcusedAbsences(int employeeId) throws SQLException {
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(GET_UNEXCUSED_ABSENCES_SQL)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 2) {
                System.out.println("Warning: Employee " + employeeId + " has exceeded 2 unexcused absences in a week!");
            }
        }
    }

    public List<Attendance> getAttendanceByEmployeeId(int employeeId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement ps = conn.prepareStatement(GET_ATTENDANCE_BY_EMPLOYEE_SQL)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance(rs.getInt("attendance_id"), rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getString("status"));
                attendanceList.add(attendance);
            }
        }
        return attendanceList;
    }
}
