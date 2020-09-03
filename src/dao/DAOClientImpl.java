package dao;

import java.sql.PreparedStatement;
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
		
		//Llamar a CSVReader, nos devuelve un parser y luego lo leemos y mandamos a la base
		
		
	}

	public void delete(Client c) throws Exception {
		
	}

	public void modified(Client c) throws Exception {
		
	}

	public List<Client> list() throws Exception {
		return null;
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

}
