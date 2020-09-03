package dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Bill;
import entity.CSVReader;
import interfaces.DAOInterfaces;

public class DAOBillImpl extends ConectionMySQL implements DAOInterfaces<Bill>{
	
	private CSVReader read;
	protected CSVParser parse;

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

	public void delete(Bill t) throws Exception {
		
	}

	public void modified(Bill t) throws Exception {
		
	}

	public List<Bill> list() throws Exception {
		return null;
	}
	
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
