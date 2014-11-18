package com.lab.proy.database.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lab.proy.database.src.DBHelper;
import com.lab.proy.database.src.IDBHelper;
import com.lab.proy.entities.Velocidad;
import com.lab.proy.utils.Utils;

public class VelocidadDAO {
	private static final Logger logger = Logger.getLogger(VelocidadDAO.class
			.getName());
	private IDBHelper helper;

	/**
	 * Método usado para devolver todos los registros de velocidades que ese
	 * tengan
	 */
	public List<Velocidad> selectVelocidades() {
		ArrayList<Velocidad> result = new ArrayList<Velocidad>();
		String query = "SELECT * FROM velocidades order by fecha";

		helper = new DBHelper();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			ps = helper.getPreparedStatement(query);
			rs = ps.executeQuery();

			// Se recorren todos los datos obtenidos de la tabla para crear la
			// entidad Velocidad y agregarla a la coleccion que se retorna
			while (rs.next()) {
				Velocidad aux = new Velocidad();

				// Se setan los valores de los del registro de velocidad
				// obtenidos desde la
				// tabla
				aux.setId(rs.getInt("id"));
				aux.setFecha(rs.getTimestamp("fecha"));
				aux.setVelocidad(rs.getDouble("velocidad"));
				result.add(aux);
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,
					"Error al conectarse con la base de datos.");
			return result;
		} finally {
			// Se cierra la conexion
			Utils.closeResource(ps, helper);
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.log(Level.SEVERE, "Error al tratar de cerrar el recurso");
			}
		}
	}

}
