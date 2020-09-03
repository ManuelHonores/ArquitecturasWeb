package entity;

import dao.DAOBillImpl;
import dao.DAOBillProductImpl;
import dao.DAOClientImpl;
import dao.DAOProductImpl;

public class Main {

	public static void main(String[] args) {
		
		try {
			DAOClientImpl daoc = new DAOClientImpl();
			DAOProductImpl daop = new DAOProductImpl();
			DAOBillImpl daob = new DAOBillImpl();
			DAOBillProductImpl daobp = new DAOBillProductImpl();
			daoc.createTable();
			daop.createTable();
			daob.createTable();
			daobp.createTable();
			daoc.add();
			daop.add();
			daob.add();
			daobp.add();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
