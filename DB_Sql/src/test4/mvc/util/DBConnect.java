package test4.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static DBConnect db = new DBConnect();
	private Connection conn;

	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";

//	기본 생성자 : getInstance()이용하여 객체생성시 초기값 저장
	private DBConnect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(url, "javalink", "javalink");
		} catch (ClassNotFoundException e1) {

		} catch (SQLException e) {

		}

	}

	// Singleton : 메소드를 통한 객체생성을 한 번만, 현재 클래스에서 생성
	public static DBConnect getInstance() {
		return db;
	}

	// DB연결된 상태의 값을 리턴한다는 메소드
	public Connection getConnection() {
		return conn;
	}

}
