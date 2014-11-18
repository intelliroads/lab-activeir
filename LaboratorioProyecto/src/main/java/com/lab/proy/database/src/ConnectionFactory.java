package com.lab.proy.database.src;

import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.sql.DataSource;

public class ConnectionFactory {

	public ConnectionFactory() {
		super();
	}
	private static final Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
	
	private  static HashMap<String,DataSource> staticDataSourceMap = new HashMap<String,DataSource>();
	
	public static Connection getConnection(String dataSourceJNDI) {
		DataSource dataSource = null;
		Connection conn = null;
		try {
			dataSource = getDataSource(dataSourceJNDI);
			conn = dataSource.getConnection();
			try {
				conn.setAutoCommit(false);
			} catch (Exception ex) {
				logger.log(Level.SEVERE, "No se puede setear el auto-commit");
			}
			return conn;
			
		} catch (Exception e) {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e1) {
				logger.log(Level.SEVERE, "Error al cerrar la conexion JDBC");
			}
		}
		return conn;
	}
	
	/**
	 * Obtiene el datasource a partir del JNDI
	 * 
	 * @param dataSourceJNDI
	 * @return
	 * @throws Exception
	 */
	public static DataSource getDataSource(String dataSourceJNDI) throws Exception {
		DataSource datasource = null;
		try{
			synchronized(staticDataSourceMap){
				datasource = staticDataSourceMap.get(dataSourceJNDI);			
			}
			if (datasource== null) {
				Context context = null;
				try {
					context = new javax.naming.InitialContext();
					datasource = (DataSource) context.lookup(dataSourceJNDI);
					synchronized(staticDataSourceMap){
						staticDataSourceMap.put(dataSourceJNDI, datasource);					
					}
				} catch (Exception e) {
				} finally {
					if (context != null) {
						try {
							context.close();
						} catch (Exception e1) {
						}
					}
				}
			}
			
		}
		catch(Exception ex){
			throw ex;
		}
		return datasource;
	}
}
