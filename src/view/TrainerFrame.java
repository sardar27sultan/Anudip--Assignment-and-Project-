package view;
import controller.TrainerController;
import model.Trainer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class TrainerFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JPanel formPanel;
	private JPanel buttonPanel;

	private JLabel lblTrainerName;
	private JLabel lblSpecialization;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblSalary;
	private JLabel lblJoiningDate;

	private JTextField txtTrainerName;
	private JTextField txtSpecialization;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtSalary;
	private JTextField txtJoiningDate;

	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnClose;

	private JTable table;
	private DefaultTableModel tableModel;

	private TrainerController controller;

	private int selectedTrainerId = -1;
	
	public TrainerFrame() {

	    initializeFrame();

	    initializeComponents();

	    addComponents();

	    registerEvents();

	    loadTrainers();

	}
	
	private void initializeFrame() {

	    setTitle("Trainer Management");

	    setSize(950,650);

	    setLocationRelativeTo(null);

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	private void initializeComponents() {

	    controller = new TrainerController();

	    mainPanel = new JPanel(new BorderLayout());

	    formPanel = new JPanel(new GridLayout(7,2,10,10));

	    buttonPanel = new JPanel(new FlowLayout());

	    lblTrainerName = new JLabel("Trainer Name");

	    lblSpecialization = new JLabel("Specialization");

	    lblPhone = new JLabel("Phone");

	    lblEmail = new JLabel("Email");

	    lblSalary = new JLabel("Salary");

	    lblJoiningDate = new JLabel("Joining Date (yyyy-mm-dd)");

	    txtTrainerName = new JTextField();

	    txtSpecialization = new JTextField();

	    txtPhone = new JTextField();

	    txtEmail = new JTextField();

	    txtSalary = new JTextField();

	    txtJoiningDate = new JTextField();

	    btnSave = new JButton("Save");

	    btnUpdate = new JButton("Update");

	    btnDelete = new JButton("Delete");

	    btnRefresh = new JButton("Refresh");

	    btnClose = new JButton("Close");

	    tableModel = new DefaultTableModel();

	    tableModel.setColumnIdentifiers(new Object[]{

	            "ID",

	            "Trainer",

	            "Specialization",

	            "Phone",

	            "Email",

	            "Salary",

	            "Joining Date"

	    });

	    table = new JTable(tableModel);

	}
	
	private void addComponents() {

	    formPanel.add(lblTrainerName);
	    formPanel.add(txtTrainerName);

	    formPanel.add(lblSpecialization);
	    formPanel.add(txtSpecialization);

	    formPanel.add(lblPhone);
	    formPanel.add(txtPhone);

	    formPanel.add(lblEmail);
	    formPanel.add(txtEmail);

	    formPanel.add(lblSalary);
	    formPanel.add(txtSalary);

	    formPanel.add(lblJoiningDate);
	    formPanel.add(txtJoiningDate);

	    buttonPanel.add(btnSave);

	    buttonPanel.add(btnUpdate);

	    buttonPanel.add(btnDelete);

	    buttonPanel.add(btnRefresh);

	    buttonPanel.add(btnClose);

	    mainPanel.add(formPanel,BorderLayout.NORTH);

	    mainPanel.add(new JScrollPane(table),BorderLayout.CENTER);

	    mainPanel.add(buttonPanel,BorderLayout.SOUTH);

	    add(mainPanel);

	}
	
	private void registerEvents() {

	    btnSave.addActionListener(e -> saveTrainer());

	    btnUpdate.addActionListener(e -> updateTrainer());

	    btnDelete.addActionListener(e -> deleteTrainer());

	    btnRefresh.addActionListener(e -> loadTrainers());

	    btnClose.addActionListener(e -> dispose());

	    table.getSelectionModel().addListSelectionListener(e -> {

	        if (e.getValueIsAdjusting())
	            return;

	        int row = table.getSelectedRow();

	        if (row < 0)
	            return;

	        if (tableModel.getValueAt(row,0) == null)
	            return;

	        selectedTrainerId =
	                (int) tableModel.getValueAt(row,0);

	        txtTrainerName.setText(
	                String.valueOf(tableModel.getValueAt(row,1)));

	        txtSpecialization.setText(
	                String.valueOf(tableModel.getValueAt(row,2)));

	        txtPhone.setText(
	                String.valueOf(tableModel.getValueAt(row,3)));

	        txtEmail.setText(
	                String.valueOf(tableModel.getValueAt(row,4)));

	        txtSalary.setText(
	                String.valueOf(tableModel.getValueAt(row,5)));

	        txtJoiningDate.setText(
	                String.valueOf(tableModel.getValueAt(row,6)));

	    });

	}

	private void loadTrainers() {

	    tableModel.setRowCount(0);

	    for (Trainer trainer : controller.getAllTrainers()) {

	        tableModel.addRow(new Object[] {

	                trainer.getTrainerId(),

	                trainer.getTrainerName(),

	                trainer.getSpecialization(),

	                trainer.getPhone(),

	                trainer.getEmail(),

	                trainer.getSalary(),

	                trainer.getJoiningDate()

	        });

	    }

	}
	
	private void clearFields() {

	    txtTrainerName.setText("");

	    txtSpecialization.setText("");

	    txtPhone.setText("");

	    txtEmail.setText("");

	    txtSalary.setText("");

	    txtJoiningDate.setText("");

	    selectedTrainerId = -1;

	}
	
	private void saveTrainer() {

	    try {

	        Trainer trainer = new Trainer();

	        trainer.setTrainerName(txtTrainerName.getText());

	        trainer.setSpecialization(txtSpecialization.getText());

	        trainer.setPhone(txtPhone.getText());

	        trainer.setEmail(txtEmail.getText());

	        trainer.setSalary(Double.parseDouble(txtSalary.getText()));

	        trainer.setJoiningDate(
	                java.sql.Date.valueOf(txtJoiningDate.getText()));

	        if (controller.addTrainer(trainer)) {

	            JOptionPane.showMessageDialog(this,
	                    "Trainer Added Successfully.");

	            clearFields();

	            loadTrainers();

	        }

	    } catch (Exception e) {

	        JOptionPane.showMessageDialog(this,
	                e.getMessage());

	    }

	}
	
	private void updateTrainer() {

	    if (selectedTrainerId == -1) {

	        JOptionPane.showMessageDialog(this,
	                "Select a trainer.");

	        return;

	    }

	    Trainer trainer = new Trainer();

	    trainer.setTrainerId(selectedTrainerId);

	    trainer.setTrainerName(txtTrainerName.getText());

	    trainer.setSpecialization(txtSpecialization.getText());

	    trainer.setPhone(txtPhone.getText());

	    trainer.setEmail(txtEmail.getText());

	    trainer.setSalary(Double.parseDouble(txtSalary.getText()));

	    trainer.setJoiningDate(
	            java.sql.Date.valueOf(txtJoiningDate.getText()));

	    if (controller.updateTrainer(trainer)) {

	        JOptionPane.showMessageDialog(this,
	                "Updated Successfully.");

	        clearFields();

	        loadTrainers();

	    }

	}
	
	private void deleteTrainer() {

	    int row = table.getSelectedRow();

	    if (row == -1) {

	        JOptionPane.showMessageDialog(this,
	                "Select a trainer.");

	        return;

	    }

	    int id = (int) tableModel.getValueAt(row, 0);

	    int option = JOptionPane.showConfirmDialog(

	            this,

	            "Delete Trainer?",

	            "Confirm",

	            JOptionPane.YES_NO_OPTION

	    );

	    if (option == JOptionPane.YES_OPTION) {

	        if (controller.deleteTrainer(id)) {

	            JOptionPane.showMessageDialog(this,
	                    "Deleted Successfully.");

	            table.clearSelection();

	            clearFields();

	            loadTrainers();

	        }

	    }

	}

}
