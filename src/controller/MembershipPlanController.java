package controller;

import java.util.List;

import dao.MembershipPlanDAO;
import model.MembershipPlan;

public class MembershipPlanController {

    private MembershipPlanDAO dao =
            new MembershipPlanDAO();

    public boolean addPlan(MembershipPlan plan){

        return dao.addPlan(plan);

    }

    public List<MembershipPlan> getAllPlans(){

        return dao.getAllPlans();

    }

    public boolean updatePlan(MembershipPlan plan){

        return dao.updatePlan(plan);

    }

    public boolean deletePlan(int id){

        return dao.deletePlan(id);

    }

}