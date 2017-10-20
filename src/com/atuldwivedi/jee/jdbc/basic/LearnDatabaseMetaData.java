package com.atuldwivedi.jee.jdbc.basic;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atuldwivedi.jee.jdbc.util.ReleaseResources;

public class LearnDatabaseMetaData {

	public static void main(String[] args) {
		Connection con = null;
		DatabaseMetaData dbmd = null;
		try {
			// Loading the driver class
			Class.forName("org.h2.Driver");
			// Establishing the connection with data source
			con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			dbmd = con.getMetaData();
			
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("UserName: " + dbmd.getUserName());
			System.out.println("Database Product Name: "
					+ dbmd.getDatabaseProductName());
			System.out.println("Database Product Version: "
					+ dbmd.getDatabaseProductVersion());
			
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			while (rs.next()) {
			  System.out.println(rs.getString(3));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ReleaseResources.closeConnection(con);
		}
	}
}
