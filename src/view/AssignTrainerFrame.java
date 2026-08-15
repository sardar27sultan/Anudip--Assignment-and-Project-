package view;

import controller.MemberTrainerController;
import model.Member;
import model.MemberTrainer;
import model.Trainer;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AssignTrainerFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JLabel lblMember;
	private JLabel lblTrainer;

	private JComboBox<String> cmbMember;
	private JComboBox<String> cmbTrainer;

	private JButton btnAssign;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnClose;

	private JTable table;
	private DefaultTableModel tableModel;

	private MemberTrainerController controller;
	
	
	public AssignTrainerFrame() {

	    initializeFrame();

	    initializeComponents();

	    addComponents();

	    registerEvents();

	    loadMembers();

	    loadTrainers();

	    loadAssignments();

	}

	private void initializeFrame() {

	    setTitle("Assign Trainer");

	    setSize(900,600);

	    setLocationRelativeTo(null);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	private void initializeComponents() {

		controller = new MemberTrainerController();

	    mainPanel = new JPanel(new BorderLayout());

	    formPanel = new JPanel(new GridLayout(2,2,10,10));

	    buttonPanel = new JPanel(new FlowLayout());

	    lblMember = new JLabel("Member");

	    lblTrainer = new JLabel("Trainer");

	    cmbMember = new JComboBox<>();

	    cmbTrainer = new JComboBox<>();

	    btnAssign = new JButton("Assign");

	    btnDelete = new JButton("Delete Assignment");

	    btnRefresh = new JButton("Refresh");

	    btnClose = new JButton("Close");

	    tableModel = new DefaultTableModel();

	    tableModel.setColumnIdentifiers(new Object[]{

	            "Member ID",

	            "Member",

	            "Trainer ID",

	            "Trainer"

	    });

	    table = new JTable(tableModel);

	}
	
	private void addComponents() {

	    formPanel.add(lblMember);

	    formPanel.add(cmbMember);

	    formPanel.add(lblTrainer);

	    formPanel.add(cmbTrainer);

	    buttonPanel.add(btnAssign);

	    buttonPanel.add(btnDelete);

	    buttonPanel.add(btnRefresh);

	    buttonPanel.add(btnClose);

	    mainPanel.add(formPanel,BorderLayout.NORTH);

	    mainPanel.add(new JScrollPane(table),BorderLayout.CENTER);

	    mainPanel.add(buttonPanel,BorderLayout.SOUTH);

	    add(mainPanel);

	}
	
	private void registerEvents() {

	    btnAssign.addActionListener(

	            e -> assignTrainer());

	    btnDelete.addActionListener(

	            e -> deleteAssignment());

	    btnRefresh.addActionListener(

	            e -> loadAssignments());

	    btnClose.addActionListener(

	            e -> dispose());

	}
	
	private void loadMembers() {

	    cmbMember.removeAllItems();

	    for (Member member : controller.getAllMembers()) {

	        cmbMember.addItem(

	                member.getMemberId()

	                + " - "

	                + member.getFullName()

	        );

	    }

	}
	
	private void loadTrainers() {

	    cmbTrainer.removeAllItems();

	    for (Trainer trainer : controller.getAllTrainers()) {

	        cmbTrainer.addItem(

	                trainer.getTrainerId()

	                + " - "

	                + trainer.getTrainerName()

	        );

	    }

	}
	
	private void loadAssignments() {

	    tableModel.setRowCount(0);

	    for (MemberTrainer assignment :

	            controller.getAssignments()) {

	        tableModel.addRow(new Object[]{

	                assignment.getMemberId(),

	                assignment.getMemberName(),

	                assignment.getTrainerId(),

	                assignment.getTrainerName()

	        });

	    }

	}
	
	private void assignTrainer() {

	    String member = cmbMember.getSelectedItem().toString();

	    String trainer = cmbTrainer.getSelectedItem().toString();

	    int memberId =
	            Integer.parseInt(member.split("-")[0].trim());

	    int trainerId =
	            Integer.parseInt(trainer.split("-")[0].trim());

	    if (controller.assignTrainer(memberId, trainerId)) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Trainer Assigned Successfully."

	        );

	        loadAssignments();

	    }

	}
	
	private void deleteAssignment() {

	    int row = table.getSelectedRow();

	    if (row == -1) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Select an assignment."

	        );

	        return;

	    }

	    int memberId =

	            (int) tableModel.getValueAt(row, 0);

	    int trainerId =

	            (int) tableModel.getValueAt(row, 2);

	    if (controller.deleteAssignment(

	            memberId,

	            trainerId)) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Assignment Deleted."

	        );

	        loadAssignments();

	    }

	}
	
}