package controller;

import java.util.List;

import dao.MemberTrainerDAO;
import model.Member;
import model.MemberTrainer;
import model.Trainer;

public class MemberTrainerController {

    private MemberTrainerDAO dao = new MemberTrainerDAO();

    public List<Member> getAllMembers() {

        return dao.getAllMembers();

    }

    public List<Trainer> getAllTrainers() {

        return dao.getAllTrainers();

    }

    public boolean assignTrainer(int memberId, int trainerId) {

        return dao.assignTrainer(memberId, trainerId);

    }

    public List<MemberTrainer> getAssignments() {

        return dao.getAssignments();

    }

    public boolean deleteAssignment(int memberId, int trainerId) {

        return dao.deleteAssignment(memberId, trainerId);

    }

}