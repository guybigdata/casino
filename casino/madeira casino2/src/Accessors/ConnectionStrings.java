package Accessors;

public class ConnectionStrings {
	private String mysqlConnection; 
	
	ConnectionStrings(){
		this.mysqlConnection = "jdbc:mysql://52.169.19.139:3306/casino?" + "user=guyhome&password=12qwaszx";
	}

	public String getMysqlConnection() {
		return mysqlConnection;
	}

	public void setMysqlConnection(String mysqlConnection) {
		this.mysqlConnection = mysqlConnection;
	}


}
