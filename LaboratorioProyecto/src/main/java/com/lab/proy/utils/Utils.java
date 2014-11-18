package com.lab.proy.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lab.proy.database.src.DBHelper;
import com.lab.proy.database.src.IDBHelper;

public class Utils {

	
	public Utils(){
		
	}
	
	
	/** Metodo que commitea la consulta 
	 * @throws SQLException 
	 * @throws Exception **/
	public static int executeUpdate(PreparedStatement ps,IDBHelper helper, boolean forUpdate) throws SQLException {
		
		ResultSet rs = null;
		
		//Valor por defecto si no inserta.
		int i = -1;
		
		try {		

			//Ejecutamos y commiteamos
			i = ps.executeUpdate();			
			
			//Si estamos insertando, quiero recuperar el id insertado.
			if (!forUpdate){
				
				//Obtenemos las claves insertadas.
				rs = ps.getGeneratedKeys();
								
				if ((rs != null) && (rs.next())) {
					i = rs.getInt(1);
				} else {
					throw new SQLException("No se pudo obtener el último id insertado en la base.");
				}			
			}
			
			//Al terminar, comiteamos.
			helper.commit();
			
			return i;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
			helper.rollBack();
			return -1;
			
		}finally{
			if (rs != null){
				rs.close();
			}
		}	
	}
	
	
	/** Metodo que finaliza la transaccion **/
	public static void closeResource(PreparedStatement ps,IDBHelper helper){
		try {
			ps.close();
			helper.releaseConnection();
			ps = null;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
}
