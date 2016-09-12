package Accessors;



public class ConnectionStrings {
	
	private final String  mysqlConnection = "jdbc:mysql://10.1.0.4:3306/casino?" + "user=guyhome&password=12qwaszx"; 
	private final int mongoConnectionPort = 27017;
	private String mongoConnectionIp = "10.1.0.5";


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
