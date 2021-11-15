package test4.mvc;

import java.util.Scanner;

import test4.mvc.dto.Product;
import test4.mvc.service.Service;
import test4.mvc.service.ServiceImpl;

public class ProductMain {

	public static void main(String[] args) {
		Service service = new ServiceImpl();
		boolean itStop = false;
		Scanner sc = new Scanner(System.in);

		while (!itStop) {
			System.out.println("1.제품등록 2.제품검색 3.전체검색 4.수정 5.삭제 6.종료");
			int number = sc.nextInt();
			switch (number) {
			case 1:
				service.addProduct();
				break;
			case 2:
				Product p = service.getProduct();
				System.out.println(p);
				break;
			case 3:
				service.getProducts();
				break;
			case 4:
				service.editProduct();
				break;
			case 5:
				service.delProduct();
				break;
			case 6:
				System.out.println("종료합니다.");
				itStop = true;
				break;
			}

		}
		sc.close();

	}

}
