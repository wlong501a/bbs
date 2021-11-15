package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProcedureTest {
	Connection conn;
	Statement stmt;
	
	static {try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
	}
		
	}
	
	public void connect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		System.out.println("Connection Success!");
		try {
			conn = DriverManager.getConnection(url, "javalink", "javalink");
		} catch (SQLException e) {
			
		}
	}
	
	//저장프로시저 호출전 데이터 입력 
	public void insert() throws SQLException{
//		stmt = conn.createStatement();
//		String query = "insert into member3 values('Alpha', '1234', 'AI', 20, 'LA', 'go@ai.com')";
//		int count = stmt.executeUpdate(query);
//		if(count > 0) {
//		System.out.println("insert success!");
//		}else {
//		System.out.println("데이터가 저장되지 않았습니다.");
//		}
		connect();
		
		PreparedStatement pstmt = null;
		String sql = "insert into member3 values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "Alpha");
		pstmt.setString(2, "1234");
		pstmt.setString(3, "AI");
		pstmt.setInt(4, 20);
		pstmt.setString(5, "LA");
		pstmt.setString(6, "go@ai.com");
		
		int count = pstmt.executeUpdate();
		if(count > 0) {
			System.out.println("insert success!");
			}else {
			System.out.println("데이터가 저장되지 않았습니다.");
			}
		pstmt.close();
		conn.close();
	}
	
	public void select() throws SQLException{
//		stmt = conn.createStatement();
//		String query = "select * from member3";
//		
//		ResultSet rs = stmt.executeQuery(query);
//		while (rs.next()) {
//		String id = rs.getString(1);
//		String passwd = rs.getString(2);
//		String name = rs.getString(3);
//		int age = rs.getInt(4);
//		String addr = rs.getString(5);
//		String email = rs.getString(6);
//		System.out.println("아이디 : " +id + "비밀번호 : " + passwd + "이름 : " + name
//						+ "나이 : " + age + "주소 : " + addr + "이메일 : " + email);
//		}
		connect();
		
		PreparedStatement pstmt = null;
		String sql = "select * from member3";
		pstmt= conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println("아이디 : " +rs.getString(1) + " 비밀번호 : " + rs.getString(2)
								+ " 이름 : " + rs.getString(3) + " 나이 : " + rs.getInt(4)
								+ " 주소 : " + rs.getString(5) + " 이메일 : " + rs.getString(6));
		}
		pstmt.close();
		conn.close();
	}
	
	//저장프로시저 호출 후 데이터 입력
	public void insertMember(){
		connect();
		CallableStatement cs = null;

		try {
			String sql = "{call user_insert(?,?,?,?,?,?)}";
			cs = conn.prepareCall(sql);
			
			cs.setString(1, "Procedure");
			cs.setString(2, "1234");
			cs.setString(3, "홍길동");
			cs.setInt(4, 19);
			cs.setString(5, "강원도");
			cs.setString(6, "hong@aa.com");
			
			int count = cs.executeUpdate();
			if(count > 0) {
				System.out.println("insert success!");
				}else {
				System.out.println("데이터가 저장되지 않았습니다.");
				}
		} catch (SQLException e) {
			
		}
	
		try {
			conn.close();
			cs.close();
		} catch (SQLException e) {
			
		}
		
	}



	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ProcedureTest pt = new ProcedureTest();
		System.out.println("*** 저장프로시저 호출 전 데이터***");
		pt.insert();
		pt.select();
		
		System.out.println("==== 저장프로시저 호출 후 데이터 ====");
		pt.insertMember();
		pt.select();

	}

}
