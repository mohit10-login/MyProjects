package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlutil {
	private static sqlutil instance=null;
	private String dbName="COMPANYDIRECTORY";
	private String sql_Host="cmpddev.database.windows.net";
	private String sql_Port="1433";
	private Connection sqlconection= null;
	private Statement st=null;
	private String databaseurl=null;
	private String databaseuser="cmpdteam";
	private String databasepwd="@Wsde246!";
	public static ResultSet rs=null;
	
	
	private sqlutil(){
		
	}
	
	public static sqlutil getinstance(){
		if(instance==null)
		{
			instance= new sqlutil();
		}
		return instance;
	}
	
	public String getDatabaseurl()
	{
		databaseurl="jdbc:sqlserver://" + sql_Host + ":" + sql_Port + ";DatabaseName=" + dbName;
		return databaseurl;
	}
	
	public Connection getSQLConnection()throws Exception
	{
		if(sqlconection==null)
		{
		getinstance().getDatabaseurl();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		sqlconection=DriverManager.getConnection(databaseurl, databaseuser, databasepwd);
		return sqlconection;
		}
		return sqlconection;
	}
	
	public ResultSet executesqlquery(String sqlQuery) throws Exception
	{
		st=sqlconection.createStatement();
		rs=st.executeQuery(sqlQuery);
		return rs;
	}

}
