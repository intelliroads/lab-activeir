package com.lab.proy.database.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHelper implements IDBHelper {
	
	private static final Logger logger = Logger.getLogger(DBHelper.class.getName());
	private Connection conn;
	
	public static final String JNDI = "java:jboss/datasources/LaboratorioProyecto";

	
	public DBHelper() {
		logger.log(Level.INFO, "Conexion Inicio -- Creando nueva conexion JDBC");
		this.conn = ConnectionFactory.getConnection(DBHelper.JNDI);
	}

	
	public DBHelper(Connection conn) {
		this.conn = conn;
	}

	
	@Override
	public void beginTransaction() {
		// No se hace nada, es una conexion JDBC que ya esta abierta.
		logger.log(Level.INFO, "Comienza una transaccion JDBC");
	}

	
	@Override
	public void commit() {
		try {
			conn.commit();
			logger.log(Level.INFO, "Transaccion JDBC commiteada");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
		}
	}

	
	@Override
	public void executeNonQuery(String stm) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(stm);
		} catch (Exception e) {
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
		}
	}

	
	@Override
	public ResultSet executeQuery(String stm) throws Exception {
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(stm);
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	
	@Override
	public void rollBack() {
		try {
			conn.rollback();
			logger.log(Level.INFO, "Transaccion JDBC rollbackeada");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
		}

	}

	
	@Override
	public PreparedStatement getPreparedStatement(String sqlQuery)
			throws SQLException {
		try {
			return conn.prepareStatement(sqlQuery);
	
		} catch (SQLException e) {
			throw e;
		}
	}
	
	/** Se setea el param para que retorne lista de ids insertados
	 * 
	 */
	@Override
	public PreparedStatement getPreparedStatementInsert(String sqlQuery)
			throws SQLException {
		try {
			return conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
	
		} catch (SQLException e) {
			throw e;
		}
	}

	
	@Override
	public void releaseConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			logger.log(Level.INFO, "Conexion Fin -- Cerrando conexion JDBC");
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Error al cerrar la conexión JDBC: " + e.getMessage());
		}
	}

}
