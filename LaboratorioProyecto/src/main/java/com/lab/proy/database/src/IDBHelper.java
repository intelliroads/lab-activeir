package com.lab.proy.database.src;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBHelper {
	
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;
	public static final int SELECT = 4;
	public static final int LIKE = 5;

	public static final String OPERATOR_OR = "OR";
	public static final String OPERATOR_AND = "AND";

									   
	public void beginTransaction();

	public void commit();

	public void executeNonQuery(String stm);

	public ResultSet executeQuery(String stm) throws Exception;

	public void rollBack();

	public PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException;
	
	//se setea el param para que retorne lista de ids insertados
	public PreparedStatement getPreparedStatementInsert(String sqlQuery) throws SQLException;

	public void releaseConnection();
	
	
}
