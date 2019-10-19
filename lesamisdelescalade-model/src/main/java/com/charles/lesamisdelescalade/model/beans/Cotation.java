package com.charles.lesamisdelescalade.model.beans;

/**
 * Bean Cotation
 * 
 * @author Charles
 *
 */
public class Cotation {
	
	int id;
	String cotation;
	
	
	
	
	public Cotation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cotation(int id, String cotation) {
		super();
		this.id = id;
		this.cotation = cotation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	@Override
	public String toString() {
		return "Cotation [id=" + id + ", cotation=" + cotation + "]";
	}
	
	

}
