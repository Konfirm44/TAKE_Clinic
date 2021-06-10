package pl.take.clinic.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
public class Visit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(columnDefinition="TIMESTAMP")
	private Calendar date;
	
	@Column(length=1023)
	private String note;
	
	@Enumerated(EnumType.ORDINAL)
	private VisitStatus status;
	
	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private Doctor doctor;
	
	@OneToMany(mappedBy="visit")
	private List<Diagnosis> diagnoses;
	
	public Visit() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public VisitStatus getStatus() {
		return status;
	}

	public void setStatus(VisitStatus status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}
}