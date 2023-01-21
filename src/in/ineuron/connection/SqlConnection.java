package in.ineuron.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnection {
	Connection connection=null;
	
	private SqlConnection(){
		
	}
	
	public static Connection getJdbcConnection() throws Exception
	{
		Connection connection=null;
		 String url = "jdbc:mysql://localhost:3306/javacourse";
        String user = "root";
        String password = "root";
        connection=DriverManager.getConnection(url,user,password);
        if (connection!=null)
        {
        	System.out.println("connection established");
        	return connection;
        }
		return connection;
	}
 public static void closeConnection(ResultSet resultset,Statement statement,Connection connection) 
 throws Exception
 {
	if (resultset!=null)
	{
		resultset.close();
	}
	if (statement!=null)
	{
		statement.close();
	}
	if (connection!=null)
	{
		connection.close();
	}

 }}
