package com.lab.proy.facade;

import java.util.List;
import java.util.logging.Logger;

import com.lab.proy.database.daos.VelocidadDAO;
import com.lab.proy.entities.Velocidad;

/** Clase que se utliza para el acceso a los datos (es un Singleton) **/
public class FachadaDatos {

	// Se opto por hacer a la fachada Singleton para tener solo una en toda la
	// aplicacion.
	private static FachadaDatos INCSTANCIA = null;
	private static final Logger logger = Logger.getLogger(FachadaDatos.class
			.getName());

	public synchronized static FachadaDatos getInstance() {
		if (INCSTANCIA == null) {
			INCSTANCIA = new FachadaDatos();
		}
		return INCSTANCIA;
	}

	public List<Velocidad> selectVelocidades() {
		return new VelocidadDAO().selectVelocidades();
	}

	private FachadaDatos() {
	}
}
