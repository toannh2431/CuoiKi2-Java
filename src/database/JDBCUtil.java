package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection(){
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			try {
				String url = "jdbc:mysql://localhost:3306/thuvien?";
				String user = "root";
				String password = "123456";
				
				c = DriverManager.getConnection(url, user, password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}
	public static void main(String[] args) {
		Connection con = JDBCUtil.getConnection();
		System.out.println(con);
	}
}
