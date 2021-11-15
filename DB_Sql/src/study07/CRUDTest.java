package study07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDTest {

	Connection con;
	Statement stmt = null;

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
		} catch (SQLException e) {
			System.out.println("주소,ID,Password가 다릅니다");
		}
	}

	public void insert() {
		try {
			stmt = con.createStatement();
			String query = "insert into member1 values('aaa','1111','홍길동','22','서울시','a@a.com')";
			int count = stmt.executeUpdate(query);
			if (count > 0) {
				System.out.println("insert success!");
			} else {
				System.out.println("데이터가 저장되지 않았습니다.");
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	public void select() {
		String query = "select*from member1";
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString("name");
				String age = rs.getString("age");
				String addr = rs.getString(3);
				String email = rs.getString("email");
				System.out.println("아이디: " + id + ", 비밀번호: " + password + ", 이름: " + name + ", 나이: " + age + ",주소: "
						+ addr + ",이메일: " + email);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void update() {
		try {
			stmt = con.createStatement();
			String query = "update member1 set addr='부산시' where id='aaa'";

			int count = stmt.executeUpdate(query);

			if (count > 0) {
				System.out.println("update success!");
			} else {
				System.out.println("업데이트가 되지 않았습니다.");
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	public void delete() {
		try {
			stmt = con.createStatement();
			String query = "delete member1 where id='aaa'";
			int count = stmt.executeUpdate(query);

			if (count > 0) {
				System.out.println("delete success!");
			} else {
				System.out.println("데이터가 삭제되지 않았습니다.");
			}
			con.close();
		} catch (SQLException e) {
		}
	}

	public static void main(String[] args) {

		CRUDTest st = new CRUDTest();

		st.connect();
		st.insert();
		System.out.println("insert 수행 후");
		st.connect();
		st.select();

		st.connect();
		st.update();
		System.out.println("update 수행 후");
		st.connect();
		st.select();

		st.connect();
		st.delete();

		System.out.println("delete 수행 후");
		st.connect();
		st.select();
	}

}
