/**
 * @author marcony.souza
 */
package br.gameClothes.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author marcony.souza
 *
 */
public class DBConnection {

private static Connection connection;
	
	static { 
		try { 
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbGameClothes",
					"SA", ""); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		} 

	public static Connection getConnection() { 
		return connection; 
		} 
	}
