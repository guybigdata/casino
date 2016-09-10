package Accessors;



public class ConnectionStrings {
	
	private final String  mysqlConnection = "jdbc:mysql://52.169.19.139:3306/casino?" + "user=guyhome&password=12qwaszx"; 
	private final int mongoConnectionPort = 27017;
	private String mongoConnectionIp = "52.164.245.164";


	public String getConnectionString() {
		return mongoConnectionIp;
	}

	public String getMysqlConnection() {
		return mysqlConnection;
	}
	
	public int getMongoConnectionPort() {
		return mongoConnectionPort;
	}
}
