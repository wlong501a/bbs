package p01.db_connection;
//내용확인
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//CRUD : Create Read Update Delete

//executeQuery(String sql): select문 사용시(들어있는 내용확인)
//executeUpdate(String sql) : insert,update,delete문 사용시
public class CRUDConnection {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
//			Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");	//oracle과 연결해서
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//주소를 따라들어가서

//DriverManager클래스	 getConnection(String url(주소), String user(데이터베이스), String password(암호))
//			결과값:static Connection인터페이스
			conn = DriverManager.getConnection(url, "javalink", "javalink");	//연결

//Connection인터페이스 createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
//			결과값:Statement인터페이스
			stmt = conn.createStatement();		//문장을 던저주고
			String query = "select*from goodsinfo";	//문장
			
//Statement인터페이스 	executeQuery(String sql)
//			결과값:ResultSet인터페이스
			ResultSet rs = stmt.executeQuery(query);	//문장을 실행해서 저장
			
			while (rs.next()) {
//			Statement인터페이스 getString(String columnLabel)
//				결과값: String클래스
				String code = rs.getString("code");
				String name = rs.getString("name");
				String price = rs.getString("price");
				String maker = rs.getString("maker");
				System.out.println(code + " : " + name + " : " + price + " : " + maker);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음 : 해당 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("정상종료");

		try {
			conn.close();
		} catch (SQLException e) {

		}

	}

}
