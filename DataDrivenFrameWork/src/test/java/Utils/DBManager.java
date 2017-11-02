package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DBManager
{
	public static Connection con = null; //sql
	public static Connection conn = null; //mysql
	
	
	//SQL Server
	public static void setDbConnection() throws SQLException, ClassNotFoundException
	{
		try{
			
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			DriverManager.setLoginTimeout(100);
			Class.forName(TestConfig.driver);
		
		//DB_URL = "jdbc:sqlserver://" + SQL_HOST + ":" + SQL_PORT + ";DatabaseName=" + db + ";user=" + SQL_USER + ";password=" + SQL_PWD;
		String DB_URL = "jdbc:sqlserver://" + TestConfig.dbHost + ":" + TestConfig.dbPortNumber + ";DatabaseName=" + TestConfig.dbName + ";user=" + TestConfig.dbUserName + ";password=" + TestConfig.dbPassword;
		con =	DriverManager.getConnection(DB_URL);
		
		
		//if(!con.isClosed())
			System.out.println("Successfully connected to SQL server");
			
	}catch(Exception e){
		System.err.println("Exception: " + e.getMessage());

		//Utils.SendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject+" - (Script failed with Error, Datamart database used for reports, connection not established)", TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);			
		}
		
		
	}
	
	public static void setMysqlDbConnection() throws SQLException, ClassNotFoundException
    {
    try
    {
        
        Class.forName (TestConfig.mysqldriver);
        conn = DriverManager.getConnection (TestConfig.mysqlurl, TestConfig.mysqluserName, TestConfig.mysqlpassword);
        if(!conn.isClosed())
			System.out.println("Successfully connected to MySQL server");
			
	
    }
    catch (Exception e)
    {
        System.err.println ("Cannot connect to database server");
       
       // Utils.SendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject+" - (Script failed with Error, Datamart database used for reports, connection not established)", TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
    }
   

}
	
	
	
	
		
	public static List<String> getQuery(String query) throws SQLException{
		
		//String Query="select top 10* from ev_call";
		Statement St = con.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> values = new ArrayList<String>();
		while(rs.next()){
		
			values.add(rs.getString(1));
			
		}
		return values;
	}
	
	public static List<String> getMysqlQuery(String query) throws SQLException{
		
		
		Statement St = conn.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> values1 = new ArrayList<String>();
		while(rs.next()){
			
			values1.add(rs.getString(1));
			
			
		}
		return values1;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(!(con==null))
		{
			return con;
		}
		else
		{
			DBManager.setDbConnection();
			return con;
		}
	}
		
}
