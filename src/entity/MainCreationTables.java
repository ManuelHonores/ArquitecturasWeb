package entity;

import dao.DAOBillImpl;
import dao.DAOBillProductImpl;
import dao.DAOClientImpl;
import dao.DAOProductImpl;
import interfaces.DAOInterfaces;

public class MainCreationTables {

	public static void main(String[] args) {
		
		try {
			DAOInterfaces<Client> daoc = new DAOClientImpl();
			DAOInterfaces<Product> daop = new DAOProductImpl();
			DAOInterfaces<Bill> daob = new DAOBillImpl();
			DAOInterfaces<BillProduct> daobp = new DAOBillProductImpl();
			daoc.createTable();
			daop.createTable();
			daob.createTable();
			daobp.createTable();
			//Los metodos add van a agregar los datos a cada una de las tablas
			daoc.add();
			daop.add();
			daob.add();
			daobp.add();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
