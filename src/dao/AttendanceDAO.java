package dao;

import database.DBConnection;
import model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    private static final String INSERT_ATTENDANCE =
            "INSERT INTO attendance(member_id, attendance_date, status) VALUES(?,?,?)";

    private static final String DELETE_ATTENDANCE =
            "DELETE FROM attendance WHERE attendance_id = ?";
    
    public boolean addAttendance(Attendance attendance) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_ATTENDANCE);

            preparedStatement.setInt(1, attendance.getMemberId());

            preparedStatement.setDate(2, attendance.getAttendanceDate());

            preparedStatement.setString(3, attendance.getStatus());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }
    
    
    public List<Attendance> getAllAttendance() {

        List<Attendance> attendanceList = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT a.attendance_id, m.full_name, a.attendance_date, a.status " +
                         "FROM attendance a " +
                         "JOIN members m ON a.member_id = m.member_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Attendance attendance = new Attendance();

                attendance.setAttendanceId(resultSet.getInt("attendance_id"));

                attendance.setMemberName(resultSet.getString("full_name"));

                attendance.setAttendanceDate(resultSet.getDate("attendance_date"));

                attendance.setStatus(resultSet.getString("status"));

                attendanceList.add(attendance);

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        return attendanceList;

    }
    
    public boolean deleteAttendance(int attendanceId) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_ATTENDANCE);

            preparedStatement.setInt(1, attendanceId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

}