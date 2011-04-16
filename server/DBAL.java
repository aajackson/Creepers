import java.sql.*;

public class DBAL
{
	// Singleton instance
	private static DBAL instance = null;
	public static DBAL getInstance()
	{
		if (instance == null)
		{
			instance = new DBAL();	
		}
		return instance;
	}
	
	// Instance Variables
	private Connection conn;
	private Statement stmt;
	private boolean isConnected;
	
	// Constructor
	protected DBAL() 
	{
		// Don't call this from outside, use getInstance()
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/cse3330a?user=root&password=root");
			stmt = conn.createStatement();
			isConnected = true;
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to connect to MySQL server.");
			System.out.println(e);
			isConnected = false;
		}
	}
	
	// Terminate Connection
	public void close()
	{
		if (!isConnected) return;
		try 
		{
			stmt.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Error with closing connection to MySQL server.");
			System.out.println(e);
		}
	}
	
	// Get a ResultSet for a query
	public ResultSet query(String query)
	{
		if (!isConnected) return null;
		try 
		{
			return stmt.executeQuery(query);
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to run query.");
			System.out.println(e);
			System.out.println("Query attempted:");
			System.out.println(query);
		}
		return null;
	}
	
	// SHA256 easy hashing method, good for passwords
	/*public static String SHA256(String text)
	{
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(text.getBytes("UTF-8"));
		byte[] digest = sha.digest();
		return new String(Hex.encode(digest));
	}*/
}