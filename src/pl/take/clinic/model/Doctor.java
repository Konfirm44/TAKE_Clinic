package pl.take.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 45)
    private String firstName;

    @Column(length = 45)
    private String lastName;

    @Column(length = 45)
    private String speciality;

    @OneToMany(mappedBy="doctor")
    @JsonIgnore
    private List<Visit> visits;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String value) {
        this.firstName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String value) {
        this.lastName = value;
    }

    public String getSpeciality() {
        return this.speciality;
    }

    private void setSpeciality(String value) {
        this.speciality = value;
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    private void setVisits(List<Visit> value) {
        this.visits = value;
    }
}
