package view;
import javax.swing.JFrame; 

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

import controller.LoginController;

import javax.swing.JOptionPane;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public LoginFrame() {
		
		initializeFrame();
	    initializeComponents();
	    addComponents();
	    registerEvents();
	}
	
		private JPanel panel;
		private JPanel headerPanel;
		private JPanel formPanel;
		private JPanel buttonPanel;
		
		private JLabel lblTitle;
		private JLabel lblUsername;
		private JLabel lblPassword;

		private JTextField txtUsername;
		private JPasswordField txtPassword;

		private JButton btnLogin;
		private JButton btnClear;
		private JButton btnExit;
		
		private LoginController loginController;
		
	    private void initializeFrame() {
	    	setTitle("Gym Management System");

	        setSize(500, 400);

	        setLocationRelativeTo(null);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        setResizable(false);
	    }

	    private void initializeComponents() {
	    	
	    	loginController = new LoginController();
	        // Main Panel
	        panel = new JPanel(new BorderLayout(20, 20));

	        // Sub Panels
	        headerPanel = new JPanel();
	        formPanel = new JPanel(new GridBagLayout());
	        buttonPanel = new JPanel();

	        // Labels
	        lblTitle = new JLabel("GYM MANAGEMENT SYSTEM");
	        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

	        lblUsername = new JLabel("Username");
	        lblPassword = new JLabel("Password");

	        // Text Fields
	        txtUsername = new JTextField(20);
	        txtPassword = new JPasswordField(20);

	        // Buttons
	        btnLogin = new JButton("Login");
	        btnClear = new JButton("Clear");
	        btnExit = new JButton("Exit");

	    }

	    private void addComponents() {

	        // ===== Header =====
	        headerPanel.add(lblTitle);

	        // ===== Form Panel =====
	        GridBagConstraints gbc = new GridBagConstraints();

	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.anchor = GridBagConstraints.WEST;

	        // Username
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        formPanel.add(lblUsername, gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        formPanel.add(txtUsername, gbc);

	        // Password
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        formPanel.add(lblPassword, gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        formPanel.add(txtPassword, gbc);

	        // ===== Button Panel =====
	        buttonPanel.add(btnLogin);
	        buttonPanel.add(btnClear);
	        buttonPanel.add(btnExit);

	        // ===== Add Panels to Main Panel =====
	        panel.add(headerPanel, BorderLayout.NORTH);
	        panel.add(formPanel, BorderLayout.CENTER);
	        panel.add(buttonPanel, BorderLayout.SOUTH);

	        // Add Main Panel to Frame
	        add(panel);
	    }

	    private void registerEvents() {
	    	btnExit.addActionListener(e -> System.exit(0));

	    	btnClear.addActionListener(e -> {

	    	    txtUsername.setText("");

	    	    txtPassword.setText("");

	    	});
	    	
	    	btnLogin.addActionListener(e -> {

	    	    String username = txtUsername.getText();

	    	    String password = new String(txtPassword.getPassword());

	    	    boolean success = loginController.login(username, password);

	    	    if(success){

	    	    	dispose();

	    	    	new DashboardFrame().setVisible(true);

	    	    }else{

	    	        JOptionPane.showMessageDialog(this,
	    	                "Invalid Username or Password");

	    	    }

	    	});
	    }
	    
	    
	}
