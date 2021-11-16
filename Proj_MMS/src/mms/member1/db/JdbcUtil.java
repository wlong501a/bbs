package mms.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	static{
		//클래스가 로딩될 때 단 한번 호출되는 영역
		//Class.forName : 특정 클래스를 메모리로 로딩하는 메소드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			
		}
		
	}
	
	public static Connection getConnection(){
		//디비 작업에 필요한 Connection 객체를 생성해 주는 메소드
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			con = DriverManager.getConnection(url, "javalink", "javalink");
			con.setAutoCommit(false);// true : commit 실행
									 // false : commit 할 수 있는 시작점(transaction 시작)
			
		} catch (SQLException e) {
			
		}
		return con;
	}

	public static void close(Connection con){
		try{
			con.close();
		}
		catch(SQLException e){
		}
	}
	public static void close(PreparedStatement pstmt){
		try{
			pstmt.close();
		}
		catch(SQLException e){
		}
	}
	public static void close(ResultSet rs){
		try{
			rs.close();
		}
		catch(SQLException e){
		}
	}
	
	
	//transaction 처리 메소드
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			
		}
	}
	public static void rollbact(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			
		}
	}


	


}
