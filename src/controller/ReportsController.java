package controller;

import dao.ReportsDAO;

public class ReportsController {

    private ReportsDAO dao = new ReportsDAO();

    public int getTotalMembers() {
        return dao.getTotalMembers();
    }

    public int getActiveMembers() {
        return dao.getActiveMembers();
    }

    public int getExpiredMembers() {
        return dao.getExpiredMembers();
    }

    public int getTotalTrainers() {
        return dao.getTotalTrainers();
    }

    public int getTotalPlans() {
        return dao.getTotalPlans();
    }

    public int getTodayAttendance() {
        return dao.getTodayAttendance();
    }

    public double getTotalPayments() {
        return dao.getTotalPayments();
    }

}