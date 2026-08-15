package view;

import controller.MemberController;
import model.Member;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewMembersFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JTable table;
    private JScrollPane scrollPane;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnClose;
    
    private JPanel searchPanel;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private JButton btnSearch;

    private DefaultTableModel tableModel;

    private MemberController memberController;

    public ViewMembersFrame() {

        initializeFrame();

        initializeComponents();

        addComponents();

        registerEvents();

        loadMembers();

    }

    private void initializeFrame() {

        setTitle("View Members");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void initializeComponents() {

        memberController = new MemberController();

        mainPanel = new JPanel(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new Object[] {
                "ID",
                "Name",
                "Gender",
                "Age",
                "Phone",
                "Email",
                "Address",
                "Plan ID",
                "Join Date",
                "Expiry Date",
                "Status"
                
        });

        table = new JTable(tableModel);

        table.setRowHeight(25);

        scrollPane = new JScrollPane(table);

        btnAdd = new JButton("Add Member");

        btnUpdate = new JButton("Update Member");

        btnDelete = new JButton("Delete Member");

        btnRefresh = new JButton("Refresh");

        btnClose = new JButton("Close");
        
        searchPanel = new JPanel();

        lblSearch = new JLabel("Search");

        txtSearch = new JTextField(20);

        btnSearch = new JButton("Search");

    }

    private void addComponents() {

    	buttonPanel.add(btnAdd);
    	buttonPanel.add(btnUpdate);
    	buttonPanel.add(btnDelete);
    	buttonPanel.add(btnRefresh);
    	buttonPanel.add(btnClose);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
    }

    private void registerEvents() {

        btnAdd.addActionListener(e -> {

            new MemberForm().setVisible(true);

        });
        
        btnSearch.addActionListener(e->{

            loadMembers(

                memberController.searchMembers(

                    txtSearch.getText()

                )

            );

        });

        btnUpdate.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please select a member.");

                return;
            }

            int memberId = (int) tableModel.getValueAt(selectedRow, 0);

            Member member = memberController.getMemberById(memberId);

            if (member != null) {

                new MemberForm(member).setVisible(true);

            }

        });

        btnDelete.addActionListener(e -> {

            deleteSelectedMember();

        });

        btnRefresh.addActionListener(e -> {

            loadMembers();

        });

        btnClose.addActionListener(e -> {

            dispose();

        });

        
        txtSearch.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent e){

                if(txtSearch.getText().trim().isEmpty()){

                    loadMembers();

                }

            }

        });
        
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    btnUpdate.doClick();

                }

            }

        });
    }

    
    private void loadMembers() {

        tableModel.setRowCount(0);

        List<Member> members = memberController.getAllMembers();

        for (Member member : members) {

            tableModel.addRow(new Object[]{

                member.getMemberId(),
                member.getFullName(),
                member.getGender(),
                member.getAge(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress(),
                member.getPlanId(),
                member.getJoinDate(),
                member.getExpiryDate(),
                member.getStatus()

            });

        }

    }


    private void loadMembers(List<Member> members) {

        tableModel.setRowCount(0);

        for (Member member : members) {

            tableModel.addRow(new Object[]{

                member.getMemberId(),
                member.getFullName(),
                member.getGender(),
                member.getAge(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress(),
                member.getPlanId(),
                member.getJoinDate(),
                member.getExpiryDate(),
                member.getStatus()

            });

        }

    }
    
    
    
    private void deleteSelectedMember() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a member.");

            return;
        }

        int memberId = (int) tableModel.getValueAt(selectedRow, 0);

        int option = JOptionPane.showConfirmDialog(

                this,

                "Delete selected member?",

                "Confirm",

                JOptionPane.YES_NO_OPTION

        );

        if (option == JOptionPane.YES_OPTION) {

            boolean success = memberController.deleteMember(memberId);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member Deleted Successfully.");

                loadMembers();
                

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Delete Failed.");

            }

        }

    }

}