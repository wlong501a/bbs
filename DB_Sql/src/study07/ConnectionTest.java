package study07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	Connection con;
//	static블럭 : 실행시 가장먼저 한 번만 실행
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없습니다.");
		}

	}

	public void connect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			con = DriverManager.getConnection(url, "javalink", "javalink");
			System.out.println("Connection Success!");
//			con.close();
		} catch (SQLException e) {
			System.out.println("주소,ID,Password가 다릅니다");
		} 

	}

	public static void main(String[] args) {
		ConnectionTest cc = new ConnectionTest();
		cc.connect();

	}

}
