package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionMySQL {
	protected Connection conection;
	
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String uri = "jdbc:mysql://localhost:3306/entregableDB";
	
	//Credenciales base de datos
	
	private final String user = "root";
	private final String password = "password";
	
	/**
	 * Se establece la conexión con la base de datos.
	 * @throws Exception
	 */
	
	public void connect() throws Exception {
		try {
			conection = DriverManager.getConnection(uri, user, password);
			conection.setAutoCommit(false);
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Cierra la conexión con la base de datos.
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if(conection != null) {
			if(!conection.isClosed()) {
				conection.close();
			}
		}
	}
}