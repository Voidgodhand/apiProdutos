package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bd_apiprodutos";
	private static String user = "root";
	private static String pass = "coti";
	
	public static Connection getConnection() throws Exception{
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pass);
	}
}
