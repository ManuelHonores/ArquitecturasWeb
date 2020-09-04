package entity;

import dao.DAOBillImpl;
import dao.DAOBillProductImpl;
import dao.DAOClientImpl;
import dao.DAOProductImpl;
import interfaces.DAOInterfaces;

/**
 * Clase main donde solamente se ejecutaran las creaciones de tablas y los metodos para agregar la información a las mismas.
 * @author Manuel Honores, Manuel Gosende, Leonardo Molleker
 *
 */

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
			
			daoc.add();
			daop.add();
			daob.add();
			daobp.add();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
