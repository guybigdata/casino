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
	

	public void savePayment(PaymentDetails paymentMethod) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement("INSERT INTO casino.CreditCards VALUES (?, ?, ?, ?, ?, ?, ?)");

	
			preparedStatement.setString(1, paymentMethod.getSocialsec());
			preparedStatement.setString(2, paymentMethod.getFirstName());
			preparedStatement.setString(3, paymentMethod.getLastName());
			preparedStatement.setString(4, paymentMethod.getCcType()); //need to bring the id from the db for generic function
			preparedStatement.setString(5, paymentMethod.getccNumber());
			preparedStatement.setString(6, paymentMethod.getExpirationDate());
			preparedStatement.setInt(7, paymentMethod.getUserId()); 


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