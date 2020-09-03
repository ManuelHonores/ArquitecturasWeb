package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.CSVReader;
import entity.Client;
import interfaces.DAOInterfaces;

public class DAOClientImpl extends ConectionMySQL implements DAOInterfaces<Client>{
	
	private CSVReader read;
	protected CSVParser parse;
	
	public void add() throws Exception {
		//Llama a la clase CSVReader, nos devuelve un parser y luego lo leemos y mandamos a la bases
		
		this.connect();
		read = new CSVReader("./CSV/clientes.csv");
		parse = read.reader();
		for(CSVRecord row: parse) {
			String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
			PreparedStatement ps = this.conection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idCliente")));
			ps.setString(2, row.get("nombre"));
			ps.setString(3, row.get("email"));
			ps.executeUpdate();
			ps.close();
			this.conection.commit();
		}
		this.close();
		
	}

	public void createTable() throws Exception {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String table = "CREATE TABLE cliente(" + 
		"idCliente INT," + 
		"nombre VARCHAR(500)," + 
		"email VARCHAR(150)," + 
		"PRIMARY KEY(idCliente))";
		this.conection.prepareStatement(table).execute();	
		this.conection.commit();
		this.close();
	}
	
	
	//Punto 4
	public List<Client> listByBilling() throws Exception {
		
		List<Client> list = new ArrayList<Client>();
		
		try {
			this.connect();
			String select = "Select cl.idCliente, cl.nombre, cl.email, sum(p.valor * fp.cantidad) AS Facturacion " + 
							"from cliente cl JOIN factura f ON (cl.idCliente = f.idCliente) " + 
							"JOIN facturaProducto fp ON (f.idFactura = fp.idFactura) " + 
							"JOIN producto p ON (fp.idProducto = p.idProducto) " + 
							"GROUP BY idCliente " + 
							"ORDER BY Facturacion DESC";
			PreparedStatement ps = this.conection.prepareStatement(select);
			
			list = new ArrayList<Client>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client per = new Client();
				per.setId(rs.getInt("idCliente"));
				per.setName(rs.getString("nombre"));
				per.setEmail(rs.getString("email"));
				list.add(per);
			}
			rs.close();
			ps.close();
			this.close();
			return list;
		}
		catch (Exception e) {
			throw e;
		} 
		
	}

}
