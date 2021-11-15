package test4.mvc.service;

import java.util.Scanner;
import java.util.Vector;
import test4.mvc.dao.Dao;
import test4.mvc.dao.OracleDao;
import test4.mvc.dto.Product;

public class ServiceImpl implements Service {
	Scanner sc = new Scanner(System.in);
	Dao dao = new OracleDao();

	@Override
	public void addProduct() { // insert
		System.out.println("제품이름은? ");
		String name = sc.next();
		System.out.println("제품가격은? ");
		int price = sc.nextInt();

		dao.insert(new Product(0, name, price));
		// new Product(0,name,price)의 num의 자리에 0 을 넣는 이유는 product_seq.nextval가 1부터 시작
	}

	@Override
	public Product getProduct() {
		System.out.println("검색할 제품번호는? ");
		int num = sc.nextInt();
		Product p = dao.select(num);
		return p;
	}

	@Override
	public Vector<Product> getProducts() {
		System.out.println("Oracle selectAll");
		Vector<Product> v = dao.selectAll();
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
		return v;
	}

	@Override
	public void delProduct() {
		System.out.println("삭제할 제품번호는? ");
		int num = sc.nextInt();
		dao.delete(num);

	}

	@Override
	public void editProduct() {
		System.out.println("수정할 제품번호는? ");
		int num = sc.nextInt();
		Product p = dao.select(num);
		
		System.out.println(p);
		
		System.out.println("새 제품이름: ");
		String name = sc.next(); 
		System.out.println("새 제품가격: ");
		int price = sc.nextInt();
		
		p.setName(name);
		p.setPrice(price);
		
		dao.update(p.getNum(), p);

	}

}
