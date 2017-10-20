package com.atuldwivedi.jee.jdbc.ddl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.atuldwivedi.jee.jdbc.util.ReleaseResources;
import com.atuldwivedi.jee.jdbc.util.connection.ConnectionFactory;

/**
 * This class contains very basic example of JDBC program for using
 * {@link Statement} interface, it creates a schema and a table.
 * 
 * This example uses h2 database as data source.
 * 
 * @author Atul Dwivedi 2016
 */
public class CreateStatement {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String createJdbcSchema = "CREATE SCHEMA IF NOT EXISTS LEARN_JDBC)";
		String createLoginTable = "CREATE TABLE LEARN_JDBC.LOGIN (USERNAME VARCHAR2, PASSWORD VARCHAR2)";

		int rowUpdatedRowCount = 0;

		try {
			con = ConnectionFactory.getConnecction();
			stmt = con.createStatement();

			rowUpdatedRowCount = stmt.executeUpdate(createJdbcSchema);

			if (rowUpdatedRowCount >= 0) {
				System.out.println("Schema created successfully.");
				rowUpdatedRowCount = stmt.executeUpdate(createLoginTable);

				if (rowUpdatedRowCount >= 0) {
					System.out.println("Table created successfully.");
				} else {
					System.out.println("Table creation failed.");
				}
			} else {
				System.out.println("Schema creation failed.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ReleaseResources.closeResultSet(rs);
			ReleaseResources.closeStatement(stmt);
			ReleaseResources.closeConnection(con);
		}
	}
}
