package p02.scott;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// scott db에 있는 emp테이블 데이터를 자바에서 출력하기
//조건 - 스캐너 입력을 받아서 empno=7902에 해당 레코드(tuple) 출력하기
//Statement => PreparedStatement
public class EmpEx3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";

		Connection conn = DriverManager.getConnection(url, "scott", "scott");

		Scanner sc = new Scanner(System.in);
		System.out.println("EMPNI 입력>");
		int no = sc.nextInt();

		String query = "select* from EMP where empno=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);

		ResultSet rs = pstmt.executeQuery();

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

		sc.close();
		conn.close();
		rs.close();
		pstmt.close();

	}

}
