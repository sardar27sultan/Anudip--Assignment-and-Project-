package controller;

import java.util.List;

import dao.MemberDAO;
import dao.PaymentDAO;
import model.Member;
import model.Payment;

public class PaymentController {

    private PaymentDAO paymentDAO = new PaymentDAO();

    private MemberDAO memberDAO = new MemberDAO();

    public boolean addPayment(Payment payment) {

        return paymentDAO.addPayment(payment);

    }

    public List<Payment> getAllPayments() {

        return paymentDAO.getAllPayments();

    }

    public boolean updatePayment(Payment payment) {

        return paymentDAO.updatePayment(payment);

    }

    public boolean deletePayment(int paymentId) {

        return paymentDAO.deletePayment(paymentId);

    }

    public List<Member> getAllMembers() {

        return memberDAO.getAllMembers();

    }

}