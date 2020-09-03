package entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVReader {
	
	private String path; 
	
	public CSVReader(String p) {
		this.path = p;
	}
	
	public CSVParser reader() throws FileNotFoundException, IOException {
		//Devolvemos un CSVParser con lo que lee del CSV y cada clase luego recorre y guarda sus datos
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(this.path));
		return parser;
	}
}
