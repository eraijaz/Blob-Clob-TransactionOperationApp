package in.ineuron.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.print.attribute.standard.Fidelity;

import in.ineuron.connection.SqlConnection;

public class ClobInsertOperation {

	public static void main(String[] args) throws FileNotFoundException
	{
		Connection connection=null;
		PreparedStatement ps=null;
		String insertQuery="insert into cities(`city`,`file`) values(?,?)";
		try {
			connection=SqlConnection.getJdbcConnection();
			if(connection!=null)
			{
				ps =connection.prepareStatement(insertQuery);
				if(ps!=null)
				{
					ps.setString(1, "sa");
					File f =new File("sample.txt");
					FileReader frs=new FileReader(f);
					
					
					ps.setCharacterStream(2, frs);
					System.out.println("inserting image from::"+f.getAbsolutePath());
					int noOfRows=ps.executeUpdate();
					if(noOfRows==1)
					{
						System.out.println("record inserted sucessfully");
					}
					else
					{
						System.out.println("No record inserted");
					}
					}
					
					
				}
			
		}
			
		catch (SQLException se) {
			se.printStackTrace();
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	
		finally {
			try {
				SqlConnection.closeConnection(null, ps, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
