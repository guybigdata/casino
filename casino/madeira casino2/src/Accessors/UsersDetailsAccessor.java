package Accessors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.UsersDetails;

public class UsersDetailsAccessor {
	
	Connection connect = null;
	Statement statement = null; 
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	ConnectionStrings connectionStringMysql = new ConnectionStrings();
	
	public void saveDetails(UsersDetails newUserDetails)
	{

				try
				{
					// load the MySQL driver, each DB has its own driver
					Class.forName("com.mysql.jdbc.Driver");

					// Setup the connection with DB
					connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

					 
					preparedStatement = connect.prepareStatement("INSERT INTO casino.UserDetails "
							+ "VALUES (default" + ",'"
							+ newUserDetails.getFirstName()+"'" + "," +"'" 
							+ newUserDetails.getLastName()
							+ "'" + "," +"'"
							+ newUserDetails.getGender()+ "'" + ",\"" 
							+ newUserDetails.getBirthDate() + "\" ,'"
							+ newUserDetails.getEmail()+"'," +"'"
							+ newUserDetails.getAddress()+ "'" + "," +"'"
							+ newUserDetails.getCountry()+ "'" +"," +"'"
							+ newUserDetails.getPhoneNumber()+ "'" +"," 
							+ newUserDetails.getUserId()+ ","
							+ "now()" + "," +"'"
							+ newUserDetails.getCity()+"');");
					preparedStatement.executeUpdate();
					//writeResultSet(resultSet);		
				} 

				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
				/*catch(ClassNotFoundException e)
				{
					System.out.println(e.getMessage());
				}*/
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
				finally 
				{
					close();
				}
			}

			private void close() 
			{
				try {
					if (resultSet != null)
					{
						resultSet.close();
					}

					if (statement != null)
					{
						statement.close();
					}

					if (connect != null)
					{
						connect.close();
					}
				} 
				catch (Exception e)
				{

				}
			}
}
	        

 


  

