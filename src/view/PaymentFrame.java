package view;
import controller.PaymentController;
import model.Member;
import model.Payment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;


public class PaymentFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JLabel lblMember;
	private JLabel lblAmount;
	private JLabel lblDate;
	private JLabel lblMethod;
	private JLabel lblRemarks;

	private JComboBox<String> cmbMember;
	private JComboBox<String> cmbMethod;

	private JTextField txtAmount;
	private JTextField txtDate;

	private JTextField txtRemarks;

	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnClose;

	private JTable table;
	private DefaultTableModel tableModel;

	private PaymentController controller;

	private int selectedPaymentId = -1;
	
	public PaymentFrame() {
		
		initializeFrame();

		initializeComponents();

		addComponents();

		registerEvents();

		loadMembers();

		loadPayments();
	}
	
	private void initializeFrame() {

	    setTitle("Payment Management");

	    setSize(950,600);

	    setLocationRelativeTo(null);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	private void initializeComponents() {

	    controller = new PaymentController();

	    mainPanel = new JPanel(new BorderLayout());

	    formPanel = new JPanel(new GridLayout(5,2,10,10));

	    buttonPanel = new JPanel(new FlowLayout());

	    lblMember = new JLabel("Member");

	    lblAmount = new JLabel("Amount");

	    lblDate = new JLabel("Payment Date (yyyy-mm-dd)");

	    lblMethod = new JLabel("Payment Method");

	    lblRemarks = new JLabel("Remarks");

	    cmbMember = new JComboBox<>();

	    cmbMethod = new JComboBox<>();

	    cmbMethod.addItem("Cash");
	    cmbMethod.addItem("UPI");
	    cmbMethod.addItem("Card");

	    txtAmount = new JTextField();

	    txtDate = new JTextField();

	    txtRemarks = new JTextField();

	    btnSave = new JButton("Save");

	    btnUpdate = new JButton("Update");

	    btnDelete = new JButton("Delete");

	    btnRefresh = new JButton("Refresh");

	    btnClose = new JButton("Close");

	    tableModel = new DefaultTableModel();

	    tableModel.setColumnIdentifiers(new Object[]{

	            "ID",
	            "Member",
	            "Amount",
	            "Payment Date",
	            "Method",
	            "Remarks"

	    });

	    table = new JTable(tableModel);

	}
	
	private void addComponents() {

	    formPanel.add(lblMember);
	    formPanel.add(cmbMember);

	    formPanel.add(lblAmount);
	    formPanel.add(txtAmount);

	    formPanel.add(lblDate);
	    formPanel.add(txtDate);

	    formPanel.add(lblMethod);
	    formPanel.add(cmbMethod);

	    formPanel.add(lblRemarks);
	    formPanel.add(txtRemarks);

	    buttonPanel.add(btnSave);

	    buttonPanel.add(btnUpdate);

	    buttonPanel.add(btnDelete);

	    buttonPanel.add(btnRefresh);

	    buttonPanel.add(btnClose);

	    mainPanel.add(formPanel, BorderLayout.NORTH);

	    mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

	    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	    add(mainPanel);

	}
	
	private void registerEvents() {

	    btnSave.addActionListener(e -> savePayment());

	    btnUpdate.addActionListener(e -> updatePayment());

	    btnDelete.addActionListener(e -> deletePayment());

	    btnRefresh.addActionListener(e -> loadPayments());

	    btnClose.addActionListener(e -> dispose());

	    table.getSelectionModel().addListSelectionListener(e -> {

	        int row = table.getSelectedRow();

	        if(row!=-1){

	            selectedPaymentId =
	                    (int)tableModel.getValueAt(row,0);

	            txtAmount.setText(
	                    tableModel.getValueAt(row,2).toString());

	            txtDate.setText(
	                    tableModel.getValueAt(row,3).toString());

	            cmbMethod.setSelectedItem(
	                    tableModel.getValueAt(row,4).toString());

	            txtRemarks.setText(
	                    tableModel.getValueAt(row,5).toString());

	        }

	    });

	}
	
	private void loadMembers() {

	    cmbMember.removeAllItems();

	    for (Member member : controller.getAllMembers()) {

	        cmbMember.addItem(

	                member.getMemberId() + " - " + member.getFullName()

	        );

	    }

	}
	
	private void loadPayments() {

	    tableModel.setRowCount(0);

	    for (Payment payment : controller.getAllPayments()) {

	        tableModel.addRow(new Object[] {

	                payment.getPaymentId(),

	                payment.getMemberName(),

	                payment.getAmount(),

	                payment.getPaymentDate(),

	                payment.getPaymentMethod(),

	                payment.getRemarks()

	        });

	    }

	}
	
	private void clearFields() {

	    cmbMember.setSelectedIndex(0);

	    txtAmount.setText("");

	    txtDate.setText("");

	    cmbMethod.setSelectedIndex(0);

	    txtRemarks.setText("");

	    selectedPaymentId = -1;

	}
	
	private void savePayment() {

	    try {

	        Payment payment = new Payment();

	        String selected = cmbMember.getSelectedItem().toString();

	        int memberId =
	                Integer.parseInt(selected.split("-")[0].trim());

	        payment.setMemberId(memberId);

	        payment.setAmount(
	                Double.parseDouble(txtAmount.getText()));

	        payment.setPaymentDate(
	                java.sql.Date.valueOf(txtDate.getText()));

	        payment.setPaymentMethod(
	                cmbMethod.getSelectedItem().toString());

	        payment.setRemarks(txtRemarks.getText());

	        if (controller.addPayment(payment)) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Payment Added Successfully.");

	            clearFields();

	            loadPayments();

	        } else {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Failed.");

	        }

	    } catch (Exception e) {

	        JOptionPane.showMessageDialog(this,
	                e.getMessage());

	    }

	}
	
	private void updatePayment() {

	    if (selectedPaymentId == -1) {

	        JOptionPane.showMessageDialog(
	                this,
	                "Select a payment.");

	        return;

	    }

	    Payment payment = new Payment();

	    String selected = cmbMember.getSelectedItem().toString();

	    int memberId =
	            Integer.parseInt(selected.split("-")[0].trim());

	    payment.setPaymentId(selectedPaymentId);

	    payment.setMemberId(memberId);

	    payment.setAmount(
	            Double.parseDouble(txtAmount.getText()));

	    payment.setPaymentDate(
	            java.sql.Date.valueOf(txtDate.getText()));

	    payment.setPaymentMethod(
	            cmbMethod.getSelectedItem().toString());

	    payment.setRemarks(txtRemarks.getText());

	    if (controller.updatePayment(payment)) {

	        JOptionPane.showMessageDialog(
	                this,
	                "Updated Successfully.");

	        clearFields();

	        loadPayments();

	    }

	}
	
	private void deletePayment() {

	    int row = table.getSelectedRow();

	    if (row == -1) {

	        JOptionPane.showMessageDialog(
	                this,
	                "Select a payment.");

	        return;

	    }

	    int id =
	            (int) tableModel.getValueAt(row,0);

	    int option = JOptionPane.showConfirmDialog(

	            this,

	            "Delete Payment?",

	            "Confirm",

	            JOptionPane.YES_NO_OPTION

	    );

	    if(option==JOptionPane.YES_OPTION){

	        if(controller.deletePayment(id)){

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Deleted Successfully.");

	            clearFields();

	            loadPayments();

	        }

	    }

	}
}
