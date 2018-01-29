package Utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Base.Base_Class;



public class DBManager
{
	private static DBManager instance=null;
	public Connection sqlServerConnection=null;
	public Connection MySqlConnection=null;
	private String dbURL=null;
	public Statement st=null;
	public ResultSet rs= null;
	
	private DBManager()
	{
	}
	
	public static DBManager getinstance()
	{
		if(instance==null)
			instance=new DBManager();
		return instance;
	}
	
	public String getDBURL()
	{
		if(dbURL==null)
		dbURL="jdbc:sqlserver://" + TestConfig.dbHost + ":" + TestConfig.dbPortNumber + ";DatabaseName=" + TestConfig.dbName;
		return dbURL;
	}
	//=============================================================================================================================
	//SQL Server
	
	public void setConnectionToSQLServer() throws Exception
	{
		//Base_Class.log.debug("Setting Up Connection with DB");
		if(sqlServerConnection==null)
		{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		DriverManager.setLoginTimeout(60);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		sqlServerConnection=DriverManager.getConnection(getDBURL(),TestConfig.dbUserName, TestConfig.dbPassword);	
		if(!sqlServerConnection.isClosed())
		{
			System.out.println("Connect with SQL Server Successfully");
			//Base_Class.log.debug("Connectin Successfully Created with DB, Name => " + TestConfig.dbName);
		}
        else
        {
        	//Base_Class.log.debug("Unable to Connect with SQL Server");
        	System.out.println("Unable to Connect with SQL Server");
        	//SendMail.sendMail(mailServer, from, to, subject, messageBody, attachmentPath, attachmentName);
        }
		}
	}
	
	public List<String> executesqlquery(String query) throws SQLException{
		st = sqlServerConnection.createStatement();
		 rs = st.executeQuery(query);
		List<String> values = new ArrayList<String>();
		while(rs.next()){
			values.add(rs.getString(1));			
		}
		return values;
	}
//=============================================================================================================================	
	//MySQL Server Connection
	
	public void setMysqlDbConnection() throws Exception
    {
        Class.forName (TestConfig.mysqldriver);
        MySqlConnection = DriverManager.getConnection (TestConfig.mysqlurl, TestConfig.mysqluserName, TestConfig.mysqlpassword);
        if(!MySqlConnection.isClosed())
			System.out.println("Successfully connected to MySQL server");
        else
        	System.out.println("Unable to Connect with MYSQL Server");
    }
	
	public List<String> getMysqlQuery(String query) throws SQLException{
		
		
		st = MySqlConnection.createStatement();
		rs = st.executeQuery(query);
		List<String> values1 = new ArrayList<String>();
		while(rs.next()){
			
			values1.add(rs.getString(1));
			
			
		}
		return values1;
	}
//=================================================================================================================
	
	public Connection getSQLServerConnection() throws Exception
	{
		if(this.sqlServerConnection!=null)
			return this.sqlServerConnection;
		else
		{
			getinstance().setConnectionToSQLServer();
			return this.sqlServerConnection;
		}
	}
	
	public Connection getMYSQLConnection() throws Exception
	{
		if(this.sqlServerConnection!=null)
		return this.MySqlConnection;
		else
		{
			getinstance().setMysqlDbConnection();
			return this.MySqlConnection;
		}		
	}
	
	public void CloseDBConnection() throws SQLException
	{
		this.sqlServerConnection.close();
		this.MySqlConnection.close();
	}
		
}
