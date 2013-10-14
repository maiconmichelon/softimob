package br.com.michelon.softimob.aplicacao.reports;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

	public static Connection getConnection() {

		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/softimob", "postgres", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}
}