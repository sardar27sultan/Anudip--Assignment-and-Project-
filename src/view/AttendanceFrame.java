package view;

import controller.AttendanceController;
import model.Member;
import model.Attendance;
import java.sql.Date;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AttendanceFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JPanel topPanel;
    private JPanel formPanel;
    private JPanel buttonPanel;

    private JLabel lblMember;
    private JLabel lblDate;
    private JLabel lblStatus;

    private JComboBox<String> cmbMember;

    private JTextField txtDate;

    private JRadioButton rbPresent;
    private JRadioButton rbAbsent;

    private ButtonGroup statusGroup;

    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnClose;

    private JTable table;
    private DefaultTableModel tableModel;

    private AttendanceController controller;
    
    public AttendanceFrame() {

        initializeFrame();

        initializeComponents();

        addComponents();

        registerEvents();
        
        loadMembers();
        
        loadAttendance();

    }

    private void initializeFrame() {

        setTitle("Attendance Management");

        setSize(900,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void initializeComponents() {
    	
    	controller = new AttendanceController();

        topPanel = new JPanel(new BorderLayout());

        formPanel = new JPanel(new GridLayout(3,2,10,10));

        buttonPanel = new JPanel(new FlowLayout());

        lblMember = new JLabel("Member");

        lblDate = new JLabel("Date (yyyy-mm-dd)");

        lblStatus = new JLabel("Status");

        cmbMember = new JComboBox<>();

        txtDate = new JTextField();

        rbPresent = new JRadioButton("Present");

        rbAbsent = new JRadioButton("Absent");

        statusGroup = new ButtonGroup();

        statusGroup.add(rbPresent);

        statusGroup.add(rbAbsent);

        btnSave = new JButton("Save");

        btnDelete = new JButton("Delete");

        btnRefresh = new JButton("Refresh");

        btnClose = new JButton("Close");

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new Object[]{

                "Attendance ID",

                "Member",

                "Date",

                "Status"

        });

        table = new JTable(tableModel);

    }

    private void addComponents() {

        formPanel.add(lblMember);

        formPanel.add(cmbMember);

        formPanel.add(lblDate);

        formPanel.add(txtDate);

        JPanel statusPanel = new JPanel();

        statusPanel.add(rbPresent);

        statusPanel.add(rbAbsent);

        formPanel.add(lblStatus);

        formPanel.add(statusPanel);

        buttonPanel.add(btnSave);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnRefresh);

        buttonPanel.add(btnClose);

        topPanel.add(formPanel,BorderLayout.NORTH);

        topPanel.add(new JScrollPane(table),BorderLayout.CENTER);

        topPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(topPanel);

    }

    private void registerEvents() {
    	
    	btnSave.addActionListener(e -> {

    	    saveAttendance();

    	});
    	
    	btnRefresh.addActionListener(e -> {

    	    loadAttendance();

    	});
    	
    	btnDelete.addActionListener(e -> {

    	    deleteAttendance();

    	});

        btnClose.addActionListener(e->dispose());
        

    }

    private void loadMembers() {

        cmbMember.removeAllItems();

        for (Member member : controller.getAllMembers()) {

            cmbMember.addItem(

                    member.getMemberId() + " - " + member.getFullName()

            );

        }

    }
    
    private void saveAttendance() {

        try {

            Attendance attendance = new Attendance();

            String selected = cmbMember.getSelectedItem().toString();

            int memberId = Integer.parseInt(selected.split("-")[0].trim());

            attendance.setMemberId(memberId);

            attendance.setAttendanceDate(Date.valueOf(txtDate.getText()));

            if (rbPresent.isSelected()) {

                attendance.setStatus("Present");

            } else {

                attendance.setStatus("Absent");

            }

            boolean success = controller.addAttendance(attendance);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Attendance Saved Successfully.");
                loadAttendance();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Save Attendance.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage());

        }

    }
    
    private void loadAttendance() {

        tableModel.setRowCount(0);

        for(Attendance attendance : controller.getAllAttendance()) {

            tableModel.addRow(new Object[] {

                attendance.getAttendanceId(),

                attendance.getMemberName(),

                attendance.getAttendanceDate(),

                attendance.getStatus()

            });

        }

    }
    
    
    private void deleteAttendance() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Please select an attendance record.");

            return;

        }

        int attendanceId = (int) tableModel.getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(

                this,

                "Delete this attendance record?",

                "Confirm",

                JOptionPane.YES_NO_OPTION

        );

        if (choice == JOptionPane.YES_OPTION) {

            if (controller.deleteAttendance(attendanceId)) {

                JOptionPane.showMessageDialog(this,
                        "Attendance Deleted Successfully.");

                loadAttendance();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Delete Failed.");

            }

        }

    }
}