package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.Payment;

public class PaymentDAO {

    private static final String INSERT_PAYMENT =
            "INSERT INTO payments(member_id,amount,payment_date,payment_method,remarks) VALUES(?,?,?,?,?)";

    private static final String GET_ALL_PAYMENTS =
            "SELECT p.payment_id,m.full_name,p.amount,p.payment_date,p.payment_method,p.remarks " +
            "FROM payments p " +
            "JOIN members m ON p.member_id=m.member_id";

    private static final String UPDATE_PAYMENT =
            "UPDATE payments SET member_id=?,amount=?,payment_date=?,payment_method=?,remarks=? WHERE payment_id=?";

    private static final String DELETE_PAYMENT =
            "DELETE FROM payments WHERE payment_id=?";

    public boolean addPayment(Payment payment) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT);

            ps.setInt(1, payment.getMemberId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getRemarks());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    public List<Payment> getAllPayments() {

        List<Payment> paymentList = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(GET_ALL_PAYMENTS);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setMemberName(rs.getString("full_name"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setRemarks(rs.getString("remarks"));

                paymentList.add(payment);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return paymentList;

    }

    public boolean updatePayment(Payment payment) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(UPDATE_PAYMENT);

            ps.setInt(1,payment.getMemberId());
            ps.setDouble(2,payment.getAmount());
            ps.setDate(3,payment.getPaymentDate());
            ps.setString(4,payment.getPaymentMethod());
            ps.setString(5,payment.getRemarks());
            ps.setInt(6,payment.getPaymentId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean deletePayment(int paymentId) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(DELETE_PAYMENT);

            ps.setInt(1,paymentId);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

}