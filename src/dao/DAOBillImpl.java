package dao;

import java.sql.PreparedStatement;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Bill;
import entity.CSVReader;
import interfaces.DAOInterfaces;

public class DAOBillImpl extends ConectionMySQL implements DAOInterfaces<Bill>{
	
	private CSVReader read;
	protected CSVParser parse;

	/**
	 * En primer lugar se genera una conexion a la base de datos.
	 * Se realiza un llamado al método reader de la clase CSVReader, enviandole la ruta para que lea un archivo .csv.
	 * Una vez obtenidos los datos, se agregan cada una de las lineas del archivo a la tabla facturas.
	 */
	
	public void add() throws Exception {
		this.connect();
		read = new CSVReader("./CSV/facturas.csv");
		parse = read.reader();
		for(CSVRecord row: parse) {
			String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
			PreparedStatement ps = this.conection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idFactura")));
			ps.setInt(2, Integer.parseInt(row.get("idCliente")));
			ps.executeUpdate();
			ps.close();
			this.conection.commit();
		}
		this.close();
	}

	/**
	 * Se genera una conexión con la base de datos.
	 * Luego se ejecuta la sentencia de creación de la tabla factura.
	 */
	
	public void createTable() throws Exception {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String table = "CREATE TABLE factura(" + 
		"idFactura INT," + 
		"idCliente INT," + 
		"PRIMARY KEY(idFactura))";
		this.conection.prepareStatement(table).execute();	
		this.conection.commit();
		this.close();
	}

}
