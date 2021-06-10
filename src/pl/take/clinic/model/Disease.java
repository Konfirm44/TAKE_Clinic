package pl.take.clinic.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Disease {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(length = 255, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private char contagious;
	
	@OneToMany(mappedBy = "disease", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Diagnosis> diagnoses;
	
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
	
	public List<Diagnosis> getDiagnoses()
	{
		return diagnoses;
	}
	
	public void setDiagnoses(List<Diagnosis> newDiagnoses)
	{
		diagnoses = newDiagnoses;
	}
}