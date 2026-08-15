package view;

import controller.MemberController;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import model.Member;

import java.sql.Date;

import javax.swing.JOptionPane;

public class MemberForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Member member;

	private boolean updateMode = false;
	
	private MemberController memberController;
	
	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JLabel lblTitle;

	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblAge;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JLabel lblPlan;
	private JLabel lblJoinDate;
	private JLabel lblExpiryDate;
	private JLabel lblStatus;

	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtJoinDate;
	private JTextField txtExpiryDate;

	private JRadioButton rbMale;
	private JRadioButton rbFemale;
	private JRadioButton rbOther;
	private ButtonGroup genderGroup;

	private JComboBox<String> cmbPlan;
	private JComboBox<String> cmbStatus;

	private JButton btnSave;
	private JButton btnReset;
	private JButton btnBack;

	
    
    public MemberForm() {

        initializeFrame();
        initializeComponents();
        addComponents();
        registerEvents();

    }
    
    private void initializeFrame() {

        setTitle("Add Member");

        setSize(750,650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    
    public MemberForm(Member member) {

        this();

        this.member = member;

        updateMode = true;

        fillForm();

        btnSave.setText("Update");

    }
    
    private void initializeComponents() {

        mainPanel = new JPanel(new BorderLayout());

        formPanel = new JPanel(new GridBagLayout());

        buttonPanel = new JPanel();

        lblTitle = new JLabel("ADD NEW MEMBER", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        lblName = new JLabel("Full Name");
        lblGender = new JLabel("Gender");
        lblAge = new JLabel("Age");
        lblPhone = new JLabel("Phone");
        lblEmail = new JLabel("Email");
        lblAddress = new JLabel("Address");
        lblPlan = new JLabel("Membership Plan");
        lblJoinDate = new JLabel("Join Date");
        lblExpiryDate = new JLabel("Expiry Date");
        lblStatus = new JLabel("Status");

        txtName = new JTextField(20);
        txtAge = new JTextField(20);
        txtPhone = new JTextField(20);
        txtEmail = new JTextField(20);
        txtAddress = new JTextField(20);
        txtJoinDate = new JTextField(20);
        txtExpiryDate = new JTextField(20);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);

        cmbPlan = new JComboBox<>(new String[]{
                "Basic",
                "Silver",
                "Gold",
                "Premium"
        });

        cmbStatus = new JComboBox<>(new String[]{
                "Active",
                "Expired"
        });

        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
        btnBack = new JButton("Back");
        
        memberController = new MemberController();
    }
    
    private void addComponents() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtName, gbc);

        JPanel genderPanel = new JPanel();

        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblGender, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblAge, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(txtAge, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(lblPlan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(cmbPlan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(lblJoinDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(txtJoinDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(lblExpiryDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(txtExpiryDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        formPanel.add(lblStatus, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(cmbStatus, gbc);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnReset);
        buttonPanel.add(btnBack);

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

    }
    
    private void registerEvents() {

        btnBack.addActionListener(e -> dispose());

        btnReset.addActionListener(e -> {

            txtName.setText("");
            txtAge.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtJoinDate.setText("");
            txtExpiryDate.setText("");

            genderGroup.clearSelection();

            cmbPlan.setSelectedIndex(0);
            cmbStatus.setSelectedIndex(0);

        });

        btnSave.addActionListener(e -> saveMember());

    }
    
    private void saveMember() {

        try {

            Member member = new Member();

            member.setFullName(txtName.getText());

            String gender = "";

            if (rbMale.isSelected())
                gender = "Male";
            else if (rbFemale.isSelected())
                gender = "Female";
            else
                gender = "Other";

            member.setGender(gender);

            member.setAge(Integer.parseInt(txtAge.getText()));

            member.setPhone(txtPhone.getText());

            member.setEmail(txtEmail.getText());

            member.setAddress(txtAddress.getText());

            member.setPlanId(cmbPlan.getSelectedIndex() + 1);

            member.setJoinDate(Date.valueOf(txtJoinDate.getText()));

            member.setExpiryDate(Date.valueOf(txtExpiryDate.getText()));

            member.setStatus(cmbStatus.getSelectedItem().toString());

            boolean success;

            if (updateMode) {

                member.setMemberId(this.member.getMemberId());

                success = memberController.updateMember(member);

                if (success) {

                    JOptionPane.showMessageDialog(this,
                            "Member Updated Successfully!");

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Update Failed.");

                }

            } else {

                success = memberController.addMember(member);

                if (success) {

                    JOptionPane.showMessageDialog(this,
                            "Member Added Successfully!");

                    clearForm();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Failed to Add Member.");

                }

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }
    
    private void fillForm() {

        txtName.setText(member.getFullName());

        txtAge.setText(String.valueOf(member.getAge()));

        txtPhone.setText(member.getPhone());

        txtEmail.setText(member.getEmail());

        txtAddress.setText(member.getAddress());

        txtJoinDate.setText(member.getJoinDate().toString());

        txtExpiryDate.setText(member.getExpiryDate().toString());

        cmbPlan.setSelectedIndex(member.getPlanId() - 1);

        cmbStatus.setSelectedItem(member.getStatus());

        switch (member.getGender()) {

            case "Male":
                rbMale.setSelected(true);
                break;

            case "Female":
                rbFemale.setSelected(true);
                break;

            default:
                rbOther.setSelected(true);
                break;
        }

    }
    
    private void clearForm() {

        txtName.setText("");

        txtAge.setText("");

        txtPhone.setText("");

        txtEmail.setText("");

        txtAddress.setText("");

        txtJoinDate.setText("");

        txtExpiryDate.setText("");

        genderGroup.clearSelection();

        cmbPlan.setSelectedIndex(0);

        cmbStatus.setSelectedIndex(0);

    }
}