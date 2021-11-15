package test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SampleDAO {
	
	ArrayList<SampleDTO> sampleDTOs = new ArrayList<SampleDTO>();
	
	public ArrayList<SampleDTO> findAll() throws ClassNotFoundException, SQLException {
		// DTO를 저장하는 리스트
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		Connection con = DriverManager.getConnection(url, "javalink", "javalink");
		Statement stmt = con.createStatement();		
		String query = "select*from book";
		ResultSet rs = stmt.executeQuery(query);

		while(rs.next()) {
			SampleDTO dto = new SampleDTO();
			String id = rs.getString(1);
			String name = rs.getString(2);
			int price = rs.getInt(3);
			dto.setId(id);
			dto.setName(name);
			dto.setPrice(price);
			sampleDTOs.add(dto);
		}

		
		return sampleDTOs;
	}

	

}
