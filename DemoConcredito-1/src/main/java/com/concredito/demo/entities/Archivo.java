package com.concredito.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "archivos")
public class Archivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "url", nullable = true)
	private String url;
	
	@Column(name = "nombre_archivo", nullable = false)
	private String nombre;
	
	@Column(name = "id_prospecto", nullable = false)
	private Long idProspecto;

	public Archivo(Long id, String url, String nombre, Long idProspecto) {
		super();
		this.id = id;
		this.url = url;
		this.nombre = nombre;
		this.idProspecto = idProspecto;
	}

	
	public Archivo(String url, String nombre) {
		super();
		this.url = url;
		this.nombre = nombre;
	}


	public Archivo() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdProspecto() {
		return idProspecto;
	}

	public void setIdProspecto(Long idProspecto) {
		this.idProspecto = idProspecto;
	}	

}
