package p02.scott;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//1. Driver 연결: Class.forName("oracle.jdbc.driver.OracleDriver");	
//				 String url = "jdbc:oracle:thin:@localhost:1521:orcl";	

//2. 계정연결: connection con = DriverManager.getConnection(url, "scott", "scott");
//			 Statement stmt = con.createStatement();

//3.Query: String query = "select*from table이름";

//4. 실행과 리턴: ResultSet rs = stmt.executeQuery(query);	/ stmt.executeUpdate(query);
//				while (rs.next()) {
//				int empno = rs.getInt("empno");
//				String ename = rs.getString("ename");
//				}

//5. DB종료: con.close();
//			rs.close();
//			conn.close();

public class EmpEx1 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			conn = DriverManager.getConnection(url, "scott", "scott");

			stmt = conn.createStatement();
			String query = "select*from EMP";

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int empno = rs.getInt(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				int mgr = rs.getInt(4);
				Date hiredate = rs.getDate(5);
				int sal = rs.getInt(6);
				int comm = rs.getInt(7);
				int deptno = rs.getInt(8);
				System.out.println(empno + " : " + ename + " : " + job + " : " + mgr + " : " + hiredate + " : " + sal
						+ " : " + comm + " : " + deptno);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음 : 해당 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("정상종료");

		try {
			conn.close();
			rs.close();
			stmt.close();
		} catch (SQLException e) {

		}

	}

}
