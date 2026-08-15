package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import database.DBConnection;
import model.Trainer;

public class TrainerDAO {

    private static final String INSERT =
            "INSERT INTO trainers(trainer_name,specialization,phone,email,salary,joining_date) VALUES(?,?,?,?,?,?)";

    private static final String GET_ALL =
            "SELECT * FROM trainers";

    private static final String UPDATE =
            "UPDATE trainers SET trainer_name=?,specialization=?,phone=?,email=?,salary=?,joining_date=? WHERE trainer_id=?";

    private static final String DELETE =
            "DELETE FROM trainers WHERE trainer_id=?";

    public boolean addTrainer(Trainer trainer) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(INSERT);

            ps.setString(1, trainer.getTrainerName());
            ps.setString(2, trainer.getSpecialization());
            ps.setString(3, trainer.getPhone());
            ps.setString(4, trainer.getEmail());
            ps.setDouble(5, trainer.getSalary());
            ps.setDate(6, trainer.getJoiningDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public List<Trainer> getAllTrainers() {

        List<Trainer> list = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Trainer trainer = new Trainer();

                trainer.setTrainerId(rs.getInt("trainer_id"));
                trainer.setTrainerName(rs.getString("trainer_name"));
                trainer.setSpecialization(rs.getString("specialization"));
                trainer.setPhone(rs.getString("phone"));
                trainer.setEmail(rs.getString("email"));
                trainer.setSalary(rs.getDouble("salary"));
                trainer.setJoiningDate(rs.getDate("joining_date"));

                list.add(trainer);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    public boolean updateTrainer(Trainer trainer) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(UPDATE);

            ps.setString(1, trainer.getTrainerName());
            ps.setString(2, trainer.getSpecialization());
            ps.setString(3, trainer.getPhone());
            ps.setString(4, trainer.getEmail());
            ps.setDouble(5, trainer.getSalary());
            ps.setDate(6, trainer.getJoiningDate());
            ps.setInt(7, trainer.getTrainerId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean deleteTrainer(int trainerId) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(DELETE);

            ps.setInt(1, trainerId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Cannot delete trainer.\nTrainer is assigned to one or more members."
            );

            e.printStackTrace();

        }

        return false;

    }

}