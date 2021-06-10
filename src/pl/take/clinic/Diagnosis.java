package pl.take.clinic;

import javax.persistence.*;

@Entity
public class Diagnosis {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(length = 255)
	private String note;

	@ManyToOne
	private Visit visit;

	@ManyToOne
	private Disease disease;

	public Diagnosis() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}
}
