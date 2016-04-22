import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase 
{
	private Connection con;
	
	public void connect()
	{
		if(con != null)
			return;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sams","root", "iitKGP");
				//Statement stm = con.createStatement();
				//ResultSet rs = stm.executeQuery("select * from loginid");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void disconnect()
	{
		if(con != null)
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection()
	{
		return this.con;
	}
}
