package view;

import java.awt.GridLayout;
import java.awt.Font;


import javax.swing.*;
import controller.ReportsController;

public class ReportsFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;

	private JLabel lblTitle;

	private JLabel lblMembers;
	private JLabel lblActive;
	private JLabel lblExpired;
	private JLabel lblTrainers;
	private JLabel lblPlans;
	private JLabel lblAttendance;
	private JLabel lblPayments;

	private JButton btnRefresh;
	private JButton btnClose;

	private ReportsController controller;
	
	public ReportsFrame() {

	    initializeFrame();

	    initializeComponents();

	    addComponents();

	    registerEvents();

	    loadReports();

	}
	
	private void initializeFrame() {

	    setTitle("Reports Dashboard");

	    setSize(600,450);

	    setLocationRelativeTo(null);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	private void initializeComponents() {

	    controller = new ReportsController();

	    panel = new JPanel(new GridLayout(10,1,10,10));

	    lblTitle = new JLabel("GYM MANAGEMENT REPORTS",JLabel.CENTER);

	    lblTitle.setFont(new Font("Arial",Font.BOLD,22));

	    lblMembers = new JLabel();

	    lblActive = new JLabel();

	    lblExpired = new JLabel();

	    lblTrainers = new JLabel();

	    lblPlans = new JLabel();

	    lblAttendance = new JLabel();

	    lblPayments = new JLabel();

	    btnRefresh = new JButton("Refresh");

	    btnClose = new JButton("Close");

	}
	
	private void addComponents() {

	    panel.add(lblTitle);

	    panel.add(lblMembers);

	    panel.add(lblActive);

	    panel.add(lblExpired);

	    panel.add(lblTrainers);

	    panel.add(lblPlans);

	    panel.add(lblAttendance);

	    panel.add(lblPayments);

	    panel.add(btnRefresh);

	    panel.add(btnClose);

	    add(panel);

	}
	
	private void loadReports() {

	    lblMembers.setText(
	            "Total Members : "
	            + controller.getTotalMembers());

	    lblActive.setText(
	            "Active Members : "
	            + controller.getActiveMembers());

	    lblExpired.setText(
	            "Expired Members : "
	            + controller.getExpiredMembers());

	    lblTrainers.setText(
	            "Total Trainers : "
	            + controller.getTotalTrainers());

	    lblPlans.setText(
	            "Membership Plans : "
	            + controller.getTotalPlans());

	    lblAttendance.setText(
	            "Today's Attendance : "
	            + controller.getTodayAttendance());

	    lblPayments.setText(
	            "Total Payments : ₹ "
	            + controller.getTotalPayments());

	}
	
	private void registerEvents() {

	    btnRefresh.addActionListener(e -> {

	        loadReports();

	    });

	    btnClose.addActionListener(e -> {

	        dispose();

	    });

	}
	
	

}
