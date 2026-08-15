package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JPanel panel;

    private JLabel lblTitle;

    
    private JButton btnManageMembers;
    
    private JButton btnLogout;
    
    private JButton btnAttendance;
    
    private JButton btnMembershipPlan;
    
    private JButton btnPayments;
    
    private JButton btnTrainer;
    
    private JButton btnAssignTrainer;

    private JButton btnReports;
    
    public DashboardFrame() {

        initializeFrame();

        initializeComponents();

        addComponents();

        registerEvents();

    }

    private void initializeFrame() {

        setTitle("Dashboard");

        setSize(700,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initializeComponents() {

        panel = new JPanel(new GridLayout(6,1,15,15));

        lblTitle = new JLabel("GYM MANAGEMENT SYSTEM", JLabel.CENTER);

        lblTitle.setFont(new Font("Arial",Font.BOLD,24));

        btnManageMembers = new JButton("Manage Members");
        
        btnAttendance = new JButton("Attendance");
        
        btnMembershipPlan = new JButton("Membership Plans");
        
        btnPayments = new JButton("Payments");
        
        btnTrainer = new JButton("Trainers");
        
        btnAssignTrainer = new JButton("Assign Trainer");
        
        btnReports = new JButton("Reports");

        btnLogout = new JButton("Logout");
        

    }

    private void addComponents() {

    	panel.add(lblTitle);

    	panel.add(btnManageMembers);

    	panel.add(btnAttendance);

    	panel.add(btnMembershipPlan);

    	panel.add(btnPayments);
    	
    	panel.add(btnTrainer);
    	
    	panel.add(btnAssignTrainer);
    	
    	panel.add(btnReports);
    	
    	panel.add(btnLogout);
        
        add(panel);

    }

    private void registerEvents() {
        
        btnManageMembers.addActionListener(e -> {

            new ViewMembersFrame().setVisible(true);

        });
        
        btnAttendance.addActionListener(e -> {

            new AttendanceFrame().setVisible(true);

        });
        
        btnMembershipPlan.addActionListener(e -> {

            new MembershipPlanFrame().setVisible(true);

        });
        
        btnPayments.addActionListener(e -> {

            new PaymentFrame().setVisible(true);

        });
        
        btnTrainer.addActionListener(e -> {

            new TrainerFrame().setVisible(true);

        });
        
        btnAssignTrainer.addActionListener(e -> {

            new AssignTrainerFrame().setVisible(true);

        });
        
        btnReports.addActionListener(e -> {
        	
        	new ReportsFrame().setVisible(true);
        
        });

        btnLogout.addActionListener(e->{

            dispose();

            new LoginFrame().setVisible(true);

        });
        

    }

}