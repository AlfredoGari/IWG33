package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="archivos")
public class Archivo implements Serializable{

	private static final long serialVersionUID = 5894390747740336364L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(length = 300, nullable = false)
	private String nombre;
	
	private long length;
	
	private Date fecha;
	
	@Column(length = 50)
	private String mime;
	
	
	@JsonIgnore
	@Lob
	private byte[] contenido;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public long getLength() {
		return length;
	}


	public void setLength(long length) {
		this.length = length;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getMime() {
		return mime;
	}


	public void setMime(String mime) {
		this.mime = mime;
	}


	public byte[] getContenido() {
		return contenido;
	}


	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	
	
	
	
}
