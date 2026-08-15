package controller;

import java.util.List;

import dao.AttendanceDAO;
import dao.MemberDAO;
import model.Attendance;
import model.Member;

public class AttendanceController {

    private MemberDAO memberDAO = new MemberDAO();

    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public List<Member> getAllMembers() {

        return memberDAO.getAllMembers();

    }

    public boolean addAttendance(Attendance attendance) {

        return attendanceDAO.addAttendance(attendance);

    }
    
    public List<Attendance> getAllAttendance() {

        return attendanceDAO.getAllAttendance();

    }
    
    public boolean deleteAttendance(int attendanceId) {

        return attendanceDAO.deleteAttendance(attendanceId);

    }

}