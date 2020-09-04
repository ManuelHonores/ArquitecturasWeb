package entity;

import java.util.ArrayList;
import java.util.List;

import dao.DAOClientImpl;
import dao.DAOProductImpl;

/**
 * TP Entregable 1
 * Clase main donde se muestran los resultados de las consignas del TP-Especial.
 * @author Manuel Honores, Manuel Gosende, Leonardo Molleker
 *
 */

public class Main {

	public static void main(String[] args) {
		
		try {
			DAOClientImpl daoc = new DAOClientImpl(); 
			DAOProductImpl daop = new DAOProductImpl();

			Product prod = new Product();
			
			prod = daop.maxCollection();
			
			List<Client> list = new ArrayList<Client>();
			
			list = daoc.listByBilling();
			
			System.out.println("El producto que mas recaud� fue: Id: " + prod.getIdProd() + " Nombre: " + prod.getName() + " Valor: " + prod.getPrice());
			
			System.out.println("");
			System.out.println("Lista de los clientes ordenados de mayor a menor segun su facturacion");
			System.out.println("");
			
			for(Client c: list) {
				
				System.out.println("Id: " + c.getId() + " Nombre: " + c.getName() + " Email: " + c.getEmail());
			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
