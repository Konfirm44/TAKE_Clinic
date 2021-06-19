package pl.take.clinic.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Disease {
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private char contagious;

	public Disease() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public char getContagious() {
		return contagious;
	}
	
	public void setContagious(char newContagious) {
		contagious = newContagious;
	}
}