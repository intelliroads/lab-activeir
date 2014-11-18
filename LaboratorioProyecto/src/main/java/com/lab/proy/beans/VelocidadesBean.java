package com.lab.proy.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.lab.proy.entities.Velocidad;
import com.lab.proy.facade.FachadaDatos;

@ManagedBean(name = "velocidadesBean")
@ViewScoped
public class VelocidadesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151009967019417882L;
	
	
	private List<Velocidad> velocidades;
	private LineChartModel modeloGrafica;
	
	
	@PostConstruct
	public void init(){
		recargarModelo();
	}
	
	public void recargarModelo(){
		velocidades = FachadaDatos.getInstance().selectVelocidades();
		modeloGrafica = new LineChartModel();
		LineChartSeries serie = new LineChartSeries();
		for(Velocidad vel:velocidades){
			serie.set(vel.getFecha(),  vel.getVelocidad());
		}
		modeloGrafica.addSeries(serie);
		
	}
	
	public String getDatatipFormat(){
		   return "<span style=\"display:none;\">%s</span><span>%s</span>";
		}

	public LineChartModel getModeloGrafica() {
		return modeloGrafica;
	}

	public void setModeloGrafica(LineChartModel modeloGrafica) {
		this.modeloGrafica = modeloGrafica;
	}

	public List<Velocidad> getVelocidades() {
		return velocidades;
	}

	public void setVelocidades(List<Velocidad> velocidades) {
		this.velocidades = velocidades;
	}
	
	
}
