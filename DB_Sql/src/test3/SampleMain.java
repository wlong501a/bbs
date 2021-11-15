package test3;

import java.sql.SQLException;
import java.util.ArrayList;

public class SampleMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		SampleDAO dao = new SampleDAO();
		ArrayList<SampleDTO> books = dao.findAll();
		for(SampleDTO s : books){
		System.out.println(s.getId());
		System.out.println(s.getName());
		System.out.println(s.getPrice());
	}
		
		
//		for(int i = 0 ; i<dao.findAll().size(); i++) {
//		System.out.println(dao.findAll().get(i).getId());
//		}
	}

}
