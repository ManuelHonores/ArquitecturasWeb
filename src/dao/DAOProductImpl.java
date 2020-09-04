package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.CSVReader;
import entity.Product;
import interfaces.DAOInterfaces;

public class DAOProductImpl extends ConectionMySQL implements DAOInterfaces<Product>{
	
	private CSVReader read;
	protected CSVParser parse;
	
	/**
	 * En primer lugar se genera una conexion a la base de datos.
	 * Se realiza un llamado al método reader de la clase CSVReader, enviandole la ruta para que lea un archivo .csv.
	 * Una vez obtenidos los datos, se agregan cada una de las lineas del archivo a la tabla producto.
	 */

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
	
	/**
	 * Se genera una conexión con la base de datos.
	 * Luego se ejecuta la sentencia de creación de la tabla producto.
	 */
	
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
	
	/**
	 * Inciso 3
	 * Se realiza una consulta para saber que producto fue el que mas recaudo (valor * cantidad)
	 * Una vez obtenido el resultado, se genera una instacia de la clase producto donde se guardaran los datos y será retornado.
	 * @return se devuelve el producto que mas recaudo.
	 * @throws Exception
	 */
	
	public Product maxCollection() throws Exception {
		try {
			Product prod = new Product();
			this.connect(); //Conecta a la base de datos MySQL
			String select = "SELECT p.idProducto, p.nombre, p.valor, sum(p.valor * fp.cantidad) AS Recaudacion "
					+ "FROM producto p JOIN facturaProducto fp ON (p.idProducto = fp.idProducto) "
					+ "GROUP BY fp.idProducto ORDER BY Recaudacion DESC LIMIT 1";
			PreparedStatement ps = this.conection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			rs.next();
			prod.setIdProd(rs.getInt(1));
			prod.setName(rs.getString(2));
			prod.setPrice(rs.getFloat(3));
			ps.close(); //Cierro conexion con la base
			this.close();
			return prod;
		}
		catch (Exception e) {
			throw e;
		}
	}

}
