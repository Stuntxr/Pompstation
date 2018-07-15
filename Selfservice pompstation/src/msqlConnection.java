import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class msqlConnection {
	
	public static void main(String[] args) throws Exception {
		dbConnection();
	}
	

	
	public static void createTable() throws Exception{
		try {
			Connection con = dbConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS testdb(id int NOT NULL AUTO_INCREMENT, firstname varchar(255), lastname varchar(255), PRIMARY KEY(id)");
			create.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			System.out.println("Table created.");
				}
		
	}
	
	
	public static Connection dbConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/testdb";
			String username = "root";
			String password = "Avarice.66";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected");
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

}
