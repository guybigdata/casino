package Accessors;

import java.sql.*;

import server.Users;

public class UsersAccessor {
	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	ConnectionStrings connectionStringMysql = new ConnectionStrings();
	Users userloginDetails;

	public void save(Users user) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement("INSERT INTO casino.Users VALUES (default, ?, ?,?)");

			// Parameters start with 1
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3,user.getUserBalance());
			preparedStatement.executeUpdate();
			
			
			//preparedStatement = connect.prepareStatement("SELECT UserId FROM casino.Users_Log");
			//resultSet = preparedStatement.executeQuery();
		
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

	public int[] FindUser(Users existsUser) {

		int id = -1;
		int balance = 0;
		try {

			
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement("SELECT UserId, Balance FROM casino.Users WHERE UserName = '"
					+ existsUser.getUserName() + "' AND Password =" + "'" 
					+ existsUser.getPassword() + "'");
// return the balance and set the member than update the member during the game and save it to database
			resultSet = preparedStatement.executeQuery();
			

			if (resultSet != null)
			{
				resultSet.next();
				id = resultSet.getInt("UserId");
				balance = resultSet.getInt("Balance");
				
			}

			
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connections after the data has been handled.
			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (Exception e) {
				}
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
				}
			if (connect != null)
				try {
					connect.close();
				} catch (Exception e) {
				}
		}
		int [] idBalance = new int[2];
		idBalance[0] = id;
		idBalance[1] = balance;
		return idBalance;

	}
	
	public void saveBalance(Users userBalance) {
			try {

				Class.forName("com.mysql.jdbc.Driver");

				connect = DriverManager
					.getConnection(connectionStringMysql.getMysqlConnection());

				preparedStatement = connect.prepareStatement("UPDATE casino.Users SET balance = (?)"
						+ " WHERE UserId = "+ userBalance.getUserId());
	
				preparedStatement.setInt(1,userBalance.getUserBalance());
				//preparedStatement.setInt(2,userBalance.getUserId());
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
	
	
}
	
