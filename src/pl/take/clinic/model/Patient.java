package pl.take.clinic.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 45)
    private String firstName;

    @Column(length = 45)
    private String lastName;

    @Column(length = 11)
    private String pesel;

    @OneToMany(mappedBy="patient")
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

    public String getPesel() {
        return this.pesel;
    }

    private void setPesel(String value) {
        this.pesel = value;
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    private void setVisits(List<Visit> value) {
        this.visits = value;
    }
}
