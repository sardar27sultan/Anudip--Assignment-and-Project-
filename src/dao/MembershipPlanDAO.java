package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.MembershipPlan;

public class MembershipPlanDAO {

    private static final String INSERT_PLAN =
            "INSERT INTO membership_plans(plan_name,duration_months,price) VALUES(?,?,?)";

    private static final String GET_ALL_PLANS =
            "SELECT * FROM membership_plans";

    private static final String UPDATE_PLAN =
            "UPDATE membership_plans SET plan_name=?,duration_months=?,price=? WHERE plan_id=?";

    private static final String DELETE_PLAN =
            "DELETE FROM membership_plans WHERE plan_id=?";

    public boolean addPlan(MembershipPlan plan) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(INSERT_PLAN);

            ps.setString(1, plan.getPlanName());
            ps.setInt(2, plan.getDurationMonths());
            ps.setDouble(3, plan.getPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public List<MembershipPlan> getAllPlans() {

        List<MembershipPlan> list = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(GET_ALL_PLANS);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                MembershipPlan plan = new MembershipPlan();

                plan.setPlanId(rs.getInt("plan_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setDurationMonths(rs.getInt("duration_months"));
                plan.setPrice(rs.getDouble("price"));

                list.add(plan);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }

    public boolean updatePlan(MembershipPlan plan){

        try{

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(UPDATE_PLAN);

            ps.setString(1,plan.getPlanName());
            ps.setInt(2,plan.getDurationMonths());
            ps.setDouble(3,plan.getPrice());
            ps.setInt(4,plan.getPlanId());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public boolean deletePlan(int id){

        try{

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(DELETE_PLAN);

            ps.setInt(1,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

}