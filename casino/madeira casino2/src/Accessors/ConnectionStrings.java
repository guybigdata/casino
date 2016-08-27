package Accessors;



public class ConnectionStrings {
	private String mysqlConnection; 
	private int ConnectionInt;
	private String ConnectionString;

	ConnectionStrings(){

		mysqlConnection = "jdbc:mysql://52.169.19.139:3306/casino?" + "user=guyhome&password=12qwaszx";
		//mongo
		ConnectionString = "52.164.245.164"; //ip
		ConnectionInt = 27017; //port
	}

	public String getConnectionString() {
		return ConnectionString;
	}


	public void setConnectionString(String connectionString) {
		ConnectionString = connectionString;
	}



	public int getConnectionInt() {
		return ConnectionInt;
	}

	public void setConnectionInt(int connectionInt) {
		ConnectionInt = connectionInt;
	}






	public String getMysqlConnection() {
		return mysqlConnection;
	}

	public void setMysqlConnection(String mysqlConnection) {
		this.mysqlConnection = mysqlConnection;
	}


}
