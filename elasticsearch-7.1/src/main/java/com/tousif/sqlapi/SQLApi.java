package com.tousif.sqlapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.elasticsearch.xpack.sql.jdbc.EsDataSource;
import java.sql.Connection;

public class SQLApi {

	public void sql() {
		
		EsDataSource dataSource = new EsDataSource();
		String address = "jdbc:es://http://localhost:9200/?timezone=UTC&page.size=250";
		Properties connectionProperties = connectionProperties(); 
		dataSource.setProperties(connectionProperties);
		
		Statement statement;
		try {
			Connection connection = dataSource.getConnection();
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(
					"SELECT business_industry FROM cfg_business_industry_esi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Properties connectionProperties() {
		return null;
	}
	
	
	
}
