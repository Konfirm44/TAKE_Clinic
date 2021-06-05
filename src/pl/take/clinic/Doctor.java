package pl.take.clinic;

import javax.persistence.*;

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
}
