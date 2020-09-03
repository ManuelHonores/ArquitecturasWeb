package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.CSVReader;
import entity.Product;
import interfaces.DAOInterfaces;

public class DAOProductImpl extends ConectionMySQL implements DAOInterfaces<Product>{
	
	private CSVReader read;
	protected CSVParser parse;

	public void add() throws Exception {
		this.connect();
		read = new CSVReader("./CSV/productos.csv");
		parse = read.reader();
		for(CSVRecord row: parse) {
			String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
			PreparedStatement ps = this.conection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idProducto")));
			ps.setString(2, row.get("nombre"));
			ps.setFloat(3, Float.parseFloat(row.get("valor")));
			ps.executeUpdate();
			ps.close();
			this.conection.commit();
		}
		this.close();
	}

	public void delete(Product t) throws Exception {
		
	}

	public void modified(Product t) throws Exception {
		
	}

	public List<Product> list() throws Exception {
		return null;
	}
	
	public void createTable() throws Exception {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String table = "CREATE TABLE producto(" + 
		"idProducto INT," + 
		"nombre VARCHAR(45)," + 
		"valor FLOAT," + 
		"PRIMARY KEY(idProducto))";
		this.conection.prepareStatement(table).execute();	
		this.conection.commit();
		this.close();
	}
	
	//Metodo para calcular que producto es el que mas recaudó
	
	public Product maxRecaudacion() throws Exception {
		try {
			Product prod = new Product();
			this.connect();; //Conecta a la base de datos MySQL
			String select = "SELECT p.idProducto, sum(p.valor * fp.cantidad) AS Recaudacion " + 
							"FROM producto p JOIN facturaProducto fp ON (p.idProducto = fp.idProducto) " + 
							"GROUP BY fp.idProducto " +
							"ORDER BY Recaudacion DESC LIMIT 1;";
			PreparedStatement ps = this.conection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			prod.setIdProd(rs.getInt(1));
			prod.setName(rs.getString(2));
			prod.setPrice(rs.getFloat(3));
			ps.close(); //Cierro conexion con la base
			this.close();;
			return prod;
		}
		catch (Exception e) {
			throw e;
		}
	}

}
