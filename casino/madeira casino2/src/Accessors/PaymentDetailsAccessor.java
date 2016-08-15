package Accessors;

import java.sql.*;

import server.PaymentDetails;

public class PaymentDetailsAccessor {
	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	int count = 0;
	ConnectionStrings connectionStringMysql = new ConnectionStrings();
	

	public void savePayment(PaymentDetails newpaymentMethod) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement("INSERT INTO casino.CreditCards VALUES (?, ?, ?, ?, ?, ?,?)");

	
			preparedStatement.setString(1, newpaymentMethod.getSocialsec());
			preparedStatement.setString(2, newpaymentMethod.getFirstName());
			preparedStatement.setString(3, newpaymentMethod.getLastName());
			preparedStatement.setString(4, newpaymentMethod.getCcType());
			preparedStatement.setString(5, newpaymentMethod.getccNumber());
			preparedStatement.setString(6, newpaymentMethod.getExpirationDate());
			preparedStatement.setInt(7, newpaymentMethod.getUserId()); 


			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}