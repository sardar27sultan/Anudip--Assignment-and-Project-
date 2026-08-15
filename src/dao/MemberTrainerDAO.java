package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.Member;
import model.MemberTrainer;
import model.Trainer;

public class MemberTrainerDAO {

    private MemberDAO memberDAO = new MemberDAO();

    private TrainerDAO trainerDAO = new TrainerDAO();

    private static final String ASSIGN =
            "INSERT INTO member_trainer(member_id,trainer_id) VALUES(?,?)";

    private static final String GET_ALL =
            "SELECT mt.member_id, m.full_name, mt.trainer_id, t.trainer_name " +
            "FROM member_trainer mt " +
            "JOIN members m ON mt.member_id = m.member_id " +
            "JOIN trainers t ON mt.trainer_id = t.trainer_id";

    private static final String DELETE =
            "DELETE FROM member_trainer WHERE member_id=? AND trainer_id=?";

    // ===========================
    // Load Members
    // ===========================

    public List<Member> getAllMembers() {

        return memberDAO.getAllMembers();

    }

    // ===========================
    // Load Trainers
    // ===========================

    public List<Trainer> getAllTrainers() {

        return trainerDAO.getAllTrainers();

    }

    // ===========================
    // Assign Trainer
    // ===========================

    public boolean assignTrainer(int memberId, int trainerId) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(ASSIGN);

            ps.setInt(1, memberId);

            ps.setInt(2, trainerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // ===========================
    // View Assignments
    // ===========================

    public List<MemberTrainer> getAssignments() {

        List<MemberTrainer> assignmentList =
                new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                MemberTrainer assignment =
                        new MemberTrainer();

                assignment.setMemberId(
                        rs.getInt("member_id"));

                assignment.setMemberName(
                        rs.getString("full_name"));

                assignment.setTrainerId(
                        rs.getInt("trainer_id"));

                assignment.setTrainerName(
                        rs.getString("trainer_name"));

                assignmentList.add(assignment);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return assignmentList;

    }

    // ===========================
    // Delete Assignment
    // ===========================

    public boolean deleteAssignment(int memberId,
                                    int trainerId) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(DELETE);

            ps.setInt(1, memberId);

            ps.setInt(2, trainerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}