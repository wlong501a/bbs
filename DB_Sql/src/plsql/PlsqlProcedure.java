package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class PlsqlProcedure {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		Oracle DB연결
//		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
//		변수 선언
		int p1Invalue = Integer.parseInt(args[0]);	//2
		int p2InOutvalue = Integer.parseInt(args[1]);	//3
		int p3Outvalue;
//		계정연결
		Connection con = DriverManager.getConnection(url, "javalink", "javalink");
		CallableStatement cs = con.prepareCall("{call compute_power(?,?,?)}");
		cs.setInt(1, p1Invalue);
		cs.setInt(2, p2InOutvalue);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.registerOutParameter(3, Types.INTEGER);
		
		cs.execute();
		
		p2InOutvalue = cs.getInt(2);
		p3Outvalue = cs.getInt(3);
		
		System.out.println(p1Invalue + "^" + p2InOutvalue + " = " + p3Outvalue);
		
		
		
		
		
	}

}
