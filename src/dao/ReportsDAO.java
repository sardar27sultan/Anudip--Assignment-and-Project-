package dao;

import java.sql.*;

import database.DBConnection;

public class ReportsDAO {

	private static final String TOTAL_MEMBERS =
			"SELECT COUNT(*) FROM members";

			private static final String ACTIVE =
			"SELECT COUNT(*) FROM members WHERE status='Active'";

			private static final String EXPIRED =
			"SELECT COUNT(*) FROM members WHERE status='Expired'";

			private static final String TRAINERS =
			"SELECT COUNT(*) FROM trainers";

			private static final String PLANS =
			"SELECT COUNT(*) FROM membership_plans";

			private static final String ATTENDANCE =
			"SELECT COUNT(*) FROM attendance WHERE attendance_date=CURDATE()";

			private static final String PAYMENTS =
			"SELECT IFNULL(SUM(amount),0) FROM payments";
			
			private int getCount(String sql) {

			    try {

			        Connection connection = DBConnection.getConnection();

			        PreparedStatement preparedStatement =
			                connection.prepareStatement(sql);

			        ResultSet resultSet =
			                preparedStatement.executeQuery();

			        if (resultSet.next()) {

			            return resultSet.getInt(1);

			        }

			    } catch (Exception e) {

			        e.printStackTrace();

			    }

			    return 0;

			}
			
			public int getTotalMembers() {

			    return getCount(TOTAL_MEMBERS);

			}

			public int getActiveMembers() {

			    return getCount(ACTIVE);

			}

			public int getExpiredMembers() {

			    return getCount(EXPIRED);

			}

			public int getTotalTrainers() {

			    return getCount(TRAINERS);

			}

			public int getTotalPlans() {

			    return getCount(PLANS);

			}

			public int getTodayAttendance() {

			    return getCount(ATTENDANCE);

			}
			
			public double getTotalPayments() {

			    try {

			        Connection connection = DBConnection.getConnection();

			        PreparedStatement ps =
			                connection.prepareStatement(PAYMENTS);

			        ResultSet rs = ps.executeQuery();

			        if(rs.next())

			            return rs.getDouble(1);

			    } catch(Exception e){

			        e.printStackTrace();

			    }

			    return 0;

			}
}
