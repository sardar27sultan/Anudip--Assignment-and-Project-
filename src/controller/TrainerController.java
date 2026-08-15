package controller;

import java.util.List;

import dao.TrainerDAO;
import model.Trainer;

public class TrainerController {

    private TrainerDAO trainerDAO = new TrainerDAO();

    public boolean addTrainer(Trainer trainer) {

        return trainerDAO.addTrainer(trainer);

    }

    public List<Trainer> getAllTrainers() {

        return trainerDAO.getAllTrainers();

    }

    public boolean updateTrainer(Trainer trainer) {

        return trainerDAO.updateTrainer(trainer);

    }

    public boolean deleteTrainer(int trainerId) {

        return trainerDAO.deleteTrainer(trainerId);

    }

}