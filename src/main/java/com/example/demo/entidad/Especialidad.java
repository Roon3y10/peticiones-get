package com.example.demo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Especialidad {
	// se declaran las propiedades de la clase entidad//
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	private String Especialidad;
	
	// constructores//
	public Especialidad() {

	}

	public Especialidad(String especialidad) {
		super();
		Especialidad = especialidad;
	}
	// sets y gets//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(String especialidad) {
		Especialidad = especialidad;
	}
	
}
