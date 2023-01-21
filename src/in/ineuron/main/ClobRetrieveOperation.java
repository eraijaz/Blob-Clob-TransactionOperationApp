package in.ineuron.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.commons.io.IOUtils;

import in.ineuron.connection.SqlConnection;

public class ClobRetrieveOperation {

	public static void main(String[] args) 
	{ Connection connection=null;
	PreparedStatement ps = null;
	ResultSet rs=null;
	FileWriter fw=null;
	String selectQuery="select city ,file from cities where city=?";
	 
	try {
		connection=SqlConnection.getJdbcConnection();
		if(connection!=null) 
		{
		ps =connection.prepareStatement(selectQuery);
		if(ps!=null)
		{
			ps.setString(1, "riyadh");
			rs=ps.executeQuery();
			if(rs.next())
			{
				String name=rs.getString(1);
				Reader frs =rs.getCharacterStream(2);
				
				String filename="11222_download.pdf";
				File file=new File(filename);
				 fw= new FileWriter(file);
				
				IOUtils.copy(frs, fw);
				fw.flush();
				System.out.println(name);
				System.out.println("file saved to the location::"+file.getAbsolutePath());
			}
			else
			{
				System.out.println("no record found");
			}
			
			
		}
		}
		
		
	}
	catch (SQLException se) {
		se.printStackTrace();
		
	} 
	catch (FileNotFoundException fe) {
		fe.printStackTrace();
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
		if(fw!=null)
		{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
		
		

	}

}
