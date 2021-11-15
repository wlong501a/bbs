package test4.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import test4.mvc.dto.Product;
import test4.mvc.util.DBConnect;

public class OracleDao implements Dao {
	private Connection conn = null; // conn의 값을 수정하지 못하도록 private 변수로 선언하고 메소드를 이용하여 값 리턴
	private PreparedStatement pstmt = null;

	public OracleDao() { // 기본생성자 : DB와 연결진행
		DBConnect dbconn = DBConnect.getInstance(); // 1. Singleton을 통한 객체생성
		conn = dbconn.getConnection(); // 2. DB연결
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public void insert(Product p) {
		String sql = "insert into product values(product_seq.nextval, ?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("insert success!");
			} else {
				System.out.println("데이터가 저장되지 않았습니다.");
			}
		} catch (SQLException e) {
		}
	}

	@Override
	public Product select(int num) { // select one
		Product p = null;
		try {
			String query = "select*from product where num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// int num = rs.getInt(1); 정해진 숫자이기에 작성(X). num은 Scanner로 받은 숫자
				String name = rs.getString("name");
				int price = rs.getInt(3);
				p = new Product(num, name, price);
			}
		} catch (SQLException e) {
		}
		return p;
	}

	@Override
	public Vector<Product> selectAll() { // select all
		Vector<Product> v = new Vector<Product>(); // null로 넣으면 예외발생..(내가 항상 실수하는거..)
		try {
			String query = "select*from product order by num asc";
			pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				v.add(new Product(num, name, price));
			}
		} catch (SQLException e) {
		}
		return v;
	}

	@Override
	public void delete(int num) { // delete
		try {
			String query = "delete product where num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("삭제가 되었습니다");
			} else {
				System.out.println("삭제가 되지 않았습니다.");
			}
		} catch (SQLException e) {
		}
	}

	@Override
	public void update(int num, Product p) { // update
		try {
			String query = "update product set name=? , price=? where num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getNum());

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("업데이트가 되었습니다");
			} else {
				System.out.println("업데이트가 되지 않았습니다.");
			}
		} catch (SQLException e) {
		}

	}

}
