package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/avaliacao";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "";
	
	public static Connection openConnection() throws Exception {
		
		// Abre uma conexão com o banco de dados
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection isConnected = openConnection();
		
		if (isConnected != null ) {
			System.out.println("Conexão obtida!");
			isConnected.close();
		}
		
	}

}
