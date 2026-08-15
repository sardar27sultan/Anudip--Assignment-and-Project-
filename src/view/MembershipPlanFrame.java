package view;

import controller.MembershipPlanController;
import model.MembershipPlan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class MembershipPlanFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel formPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;

    private JLabel lblPlanName;
    private JLabel lblDuration;
    private JLabel lblPrice;

    private JTextField txtPlanName;
    private JTextField txtDuration;
    private JTextField txtPrice;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnClose;

    private JTable table;
    private DefaultTableModel tableModel;

    private MembershipPlanController controller;

    private int selectedPlanId = -1;

    // Constructor
    public MembershipPlanFrame() {

        setTitle("Membership Plans");

        setSize(700,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        
        initializeFrame();

        initializeComponents();

        addComponents();

        registerEvents();

        loadPlans();

    }
    
    private void initializeFrame(){

        setTitle("Membership Plans");

        setSize(800,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    
    private void initializeComponents(){

        controller = new MembershipPlanController();

        mainPanel = new JPanel(new BorderLayout());

        formPanel = new JPanel(new GridLayout(3,2,10,10));

        buttonPanel = new JPanel(new FlowLayout());

        lblPlanName = new JLabel("Plan Name");

        lblDuration = new JLabel("Duration (Months)");

        lblPrice = new JLabel("Price");

        txtPlanName = new JTextField();

        txtDuration = new JTextField();

        txtPrice = new JTextField();

        btnSave = new JButton("Save");

        btnUpdate = new JButton("Update");

        btnDelete = new JButton("Delete");

        btnRefresh = new JButton("Refresh");

        btnClose = new JButton("Close");

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new Object[]{

                "ID",

                "Plan",

                "Duration",

                "Price"

        });

        table = new JTable(tableModel);

    }
    
    private void addComponents(){

        formPanel.add(lblPlanName);
        formPanel.add(txtPlanName);

        formPanel.add(lblDuration);
        formPanel.add(txtDuration);

        formPanel.add(lblPrice);
        formPanel.add(txtPrice);

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

        btnSave.addActionListener(e -> savePlan());

        btnUpdate.addActionListener(e -> updatePlan());

        btnDelete.addActionListener(e -> deletePlan());

        btnRefresh.addActionListener(e -> loadPlans());

        btnClose.addActionListener(e -> dispose());

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row != -1) {

                selectedPlanId = (int) tableModel.getValueAt(row, 0);

                txtPlanName.setText(tableModel.getValueAt(row, 1).toString());

                txtDuration.setText(tableModel.getValueAt(row, 2).toString());

                txtPrice.setText(tableModel.getValueAt(row, 3).toString());

            }

        });

    }
    
    private void loadPlans() {

        tableModel.setRowCount(0);

        List<MembershipPlan> planList = controller.getAllPlans();

        for (MembershipPlan plan : planList) {

            tableModel.addRow(new Object[]{

                    plan.getPlanId(),

                    plan.getPlanName(),

                    plan.getDurationMonths(),

                    plan.getPrice()

            });

        }

    }
    
    private void clearFields() {

        txtPlanName.setText("");

        txtDuration.setText("");

        txtPrice.setText("");

        selectedPlanId = -1;

    }
    
    private void savePlan() {

        try {

            MembershipPlan plan = new MembershipPlan();

            plan.setPlanName(txtPlanName.getText());

            plan.setDurationMonths(Integer.parseInt(txtDuration.getText()));

            plan.setPrice(Double.parseDouble(txtPrice.getText()));

            if (controller.addPlan(plan)) {

                JOptionPane.showMessageDialog(this, "Plan Added Successfully.");

                clearFields();

                loadPlans();

            } else {

                JOptionPane.showMessageDialog(this, "Failed.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }
    
    private void updatePlan() {

        if (selectedPlanId == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a plan first.");

            return;

        }

        MembershipPlan plan = new MembershipPlan();

        plan.setPlanId(selectedPlanId);

        plan.setPlanName(txtPlanName.getText());

        plan.setDurationMonths(Integer.parseInt(txtDuration.getText()));

        plan.setPrice(Double.parseDouble(txtPrice.getText()));

        if (controller.updatePlan(plan)) {

            JOptionPane.showMessageDialog(this,
                    "Updated Successfully.");

            clearFields();

            loadPlans();

        }

    }
    
    private void deletePlan() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a plan.");

            return;

        }

        int id = (int) tableModel.getValueAt(row, 0);

        int option = JOptionPane.showConfirmDialog(

                this,

                "Delete this plan?",

                "Confirm",

                JOptionPane.YES_NO_OPTION

        );

        if (option == JOptionPane.YES_OPTION) {

            if (controller.deletePlan(id)) {

                JOptionPane.showMessageDialog(this,
                        "Deleted Successfully.");

                clearFields();

                loadPlans();

            }

        }

    }
    

}