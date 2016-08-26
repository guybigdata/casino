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
	
	public void saveDetails(UsersDetails userDetails)
	{

				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

					preparedStatement = connect.prepareStatement("INSERT INTO casino.UserDetails "
							+ "VALUES ("+userDetails.getUserId() + ",'"
							+ userDetails.getFirstName()+"'" + "," +"'" 
							+ userDetails.getLastName()
							+ "'" + "," +"'"
							+ userDetails.getGender()+ "'" + ",\"" 
							+ userDetails.getBirthDate() + "\" ,'"
							+ userDetails.getEmail()+"'," +"'"
							+ userDetails.getStreet()+ "'" + "," +"'"
							+ userDetails.getCountry()+ "'" +"," +"'"
							+ userDetails.getPhoneNumber()+ "'" +"," 
							+ "now()" + "," +"'"
							+ userDetails.getCityId()+"');");
					preparedStatement.executeUpdate();
							
				} 

				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			
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
			
			public String getCountryId(UsersDetails country){
				
				String id = null;
					
				try {
	
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

						preparedStatement = connect.prepareStatement("SELECT code FROM casino.countries WHERE name_en = '"
								+ country.getCountry() + "'");
			
						resultSet = preparedStatement.executeQuery();
						
						if (resultSet != null)
						{
							resultSet.next();
							id = resultSet.getString("code");
							return id;
						}
					}

				catch (SQLException e) {
						e.printStackTrace();
					}
				catch (Exception e) {
						e.printStackTrace();
				} finally {
					
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
					
				return id;	

				}
			
	 public int getCityIdFromDb(UsersDetails cityId){
			int Id = 0;
				
			try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

					preparedStatement = connect.prepareStatement("SELECT CityID FROM casino.City WHERE City = '"
							+ cityId.getCity() + "'");
		
					resultSet = preparedStatement.executeQuery();
					

					if (resultSet != null)
					{
						resultSet.next();
						Id = resultSet.getInt("CityID");
						return Id;
						
					}
				}

			catch (SQLException e) {
					e.printStackTrace();
				}
			catch (Exception e) {
					e.printStackTrace();
			} finally {
				
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
			return Id;
	 }
}

	