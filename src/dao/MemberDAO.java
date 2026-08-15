package dao;

import database.DBConnection;
import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class MemberDAO {

    private static final String INSERT_MEMBER =
            "INSERT INTO members(full_name, gender, age, phone, email, address, plan_id, join_date, expiry_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String GET_ALL_MEMBERS =
            "SELECT * FROM members";
    
    private static final String GET_MEMBER_BY_ID =
            "SELECT * FROM members WHERE member_id = ?";
    
    private static final String UPDATE_MEMBER =
            "UPDATE members SET full_name=?, gender=?, age=?, phone=?, email=?, address=?, plan_id=?, join_date=?, expiry_date=?, status=? WHERE member_id=?";
    
    private static final String DELETE_MEMBER =
            "DELETE FROM members WHERE member_id = ?";
    
    public boolean addMember(Member member) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            // Get database connection
            connection = DBConnection.getConnection();

            // Prepare SQL statement
            preparedStatement = connection.prepareStatement(INSERT_MEMBER);

            // Set values
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setString(2, member.getGender());
            preparedStatement.setInt(3, member.getAge());
            preparedStatement.setString(4, member.getPhone());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setInt(7, member.getPlanId());
            preparedStatement.setDate(8, member.getJoinDate());
            preparedStatement.setDate(9, member.getExpiryDate());
            preparedStatement.setString(10, member.getStatus());

            // Execute INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        return false;
    }
    
    	public List<Member> getAllMembers() {

    		List<Member> memberList = new ArrayList<>();

    		Connection connection = null;
    		PreparedStatement preparedStatement = null;
    		ResultSet resultSet = null;
    		
    		try {

    		    connection = DBConnection.getConnection();

    		    preparedStatement = connection.prepareStatement(GET_ALL_MEMBERS);

    		    resultSet = preparedStatement.executeQuery();
    		    
    		    while (resultSet.next()) {
    		    	
    		    	Member member = new Member();
    		    	
    		    	member.setMemberId(resultSet.getInt("member_id"));
    		    	member.setFullName(resultSet.getString("full_name"));
    		    	member.setGender(resultSet.getString("gender"));
    		    	member.setAge(resultSet.getInt("age"));
    		    	member.setPhone(resultSet.getString("phone"));
    		    	member.setEmail(resultSet.getString("email"));
    		    	member.setAddress(resultSet.getString("address"));
    		    	member.setPlanId(resultSet.getInt("plan_id"));
    		    	member.setJoinDate(resultSet.getDate("join_date"));
    		    	member.setExpiryDate(resultSet.getDate("expiry_date"));
    		    	member.setStatus(resultSet.getString("status"));
    		    	
    		    	memberList.add(member);
    		    }

    		} catch (SQLException e) {

    		    e.printStackTrace();

    		}
    		finally {

    		    try {

    		        if (resultSet != null)
    		            resultSet.close();

    		        if (preparedStatement != null)
    		            preparedStatement.close();

    		    } catch (SQLException e) {

    		        e.printStackTrace();

    		    }

    		}
    		
    		return memberList;

    	}
    	
    	public Member getMemberById(int memberId) {

    	    Connection connection = null;
    	    PreparedStatement preparedStatement = null;
    	    ResultSet resultSet = null;

    	    try {

    	        connection = DBConnection.getConnection();

    	        preparedStatement = connection.prepareStatement(GET_MEMBER_BY_ID);

    	        preparedStatement.setInt(1, memberId);

    	        resultSet = preparedStatement.executeQuery();

    	        if (resultSet.next()) {

    	            Member member = new Member();

    	            member.setMemberId(resultSet.getInt("member_id"));
    	            member.setFullName(resultSet.getString("full_name"));
    	            member.setGender(resultSet.getString("gender"));
    	            member.setAge(resultSet.getInt("age"));
    	            member.setPhone(resultSet.getString("phone"));
    	            member.setEmail(resultSet.getString("email"));
    	            member.setAddress(resultSet.getString("address"));
    	            member.setPlanId(resultSet.getInt("plan_id"));
    	            member.setJoinDate(resultSet.getDate("join_date"));
    	            member.setExpiryDate(resultSet.getDate("expiry_date"));
    	            member.setStatus(resultSet.getString("status"));

    	            return member;

    	        }

    	    } catch (SQLException e) {

    	        e.printStackTrace();

    	    } finally {

    	        try {

    	            if (resultSet != null)
    	                resultSet.close();

    	            if (preparedStatement != null)
    	                preparedStatement.close();

    	        } catch (SQLException e) {

    	            e.printStackTrace();

    	        }

    	    }

    	    return null;

    	}
    	
    	public boolean updateMember(Member member) {

    	    Connection connection = null;
    	    PreparedStatement preparedStatement = null;

    	    try {

    	        connection = DBConnection.getConnection();

    	        preparedStatement = connection.prepareStatement(UPDATE_MEMBER);

    	        preparedStatement.setString(1, member.getFullName());
    	        preparedStatement.setString(2, member.getGender());
    	        preparedStatement.setInt(3, member.getAge());
    	        preparedStatement.setString(4, member.getPhone());
    	        preparedStatement.setString(5, member.getEmail());
    	        preparedStatement.setString(6, member.getAddress());
    	        preparedStatement.setInt(7, member.getPlanId());
    	        preparedStatement.setDate(8, member.getJoinDate());
    	        preparedStatement.setDate(9, member.getExpiryDate());
    	        preparedStatement.setString(10, member.getStatus());

    	        preparedStatement.setInt(11, member.getMemberId());

    	        return preparedStatement.executeUpdate() > 0;

    	    } catch (SQLException e) {

    	        e.printStackTrace();

    	    } finally {

    	        try {

    	            if (preparedStatement != null)
    	                preparedStatement.close();

    	        } catch (SQLException e) {

    	            e.printStackTrace();

    	        }

    	    }

    	    return false;

    	}
    	
    	public boolean deleteMember(int memberId) {

    	    Connection connection = null;
    	    PreparedStatement preparedStatement = null;

    	    try {

    	        connection = DBConnection.getConnection();

    	        preparedStatement = connection.prepareStatement(DELETE_MEMBER);

    	        preparedStatement.setInt(1, memberId);

    	        return preparedStatement.executeUpdate() > 0;

    	    } catch (SQLException e) {

    	        e.printStackTrace();

    	    } finally {

    	        try {

    	            if (preparedStatement != null)
    	                preparedStatement.close();

    	        } catch (SQLException e) {

    	            e.printStackTrace();

    	        }

    	    }

    	    return false;

    	}
    	
    	public List<Member> searchMembers(String keyword) {

    	    List<Member> members = new ArrayList<>();

    	    Connection connection = null;
    	    PreparedStatement preparedStatement = null;
    	    ResultSet resultSet = null;

    	    try {

    	        connection = DBConnection.getConnection();

    	        preparedStatement = connection.prepareStatement(

    	            "SELECT * FROM members WHERE full_name LIKE ? OR phone LIKE ?"

    	        );

    	        preparedStatement.setString(1,"%"+keyword+"%");
    	        preparedStatement.setString(2,"%"+keyword+"%");

    	        resultSet = preparedStatement.executeQuery();

    	        while(resultSet.next()){

    	            Member member = new Member();

    	            member.setMemberId(resultSet.getInt("member_id"));
    	            member.setFullName(resultSet.getString("full_name"));
    	            member.setGender(resultSet.getString("gender"));
    	            member.setAge(resultSet.getInt("age"));
    	            member.setPhone(resultSet.getString("phone"));
    	            member.setEmail(resultSet.getString("email"));
    	            member.setAddress(resultSet.getString("address"));
    	            member.setPlanId(resultSet.getInt("plan_id"));
    	            member.setJoinDate(resultSet.getDate("join_date"));
    	            member.setExpiryDate(resultSet.getDate("expiry_date"));
    	            member.setStatus(resultSet.getString("status"));

    	            members.add(member);

    	        }

    	    }catch(SQLException e){

    	        e.printStackTrace();

    	    }

    	    return members;

    	}
}