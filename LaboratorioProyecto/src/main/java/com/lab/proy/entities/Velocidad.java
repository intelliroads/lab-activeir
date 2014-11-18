package com.lab.proy.entities;

import java.io.Serializable;
import java.util.Date;

public class Velocidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4744723615796628282L;
	private Integer id;
	private Date fecha;
	private Double velocidad;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}
}
