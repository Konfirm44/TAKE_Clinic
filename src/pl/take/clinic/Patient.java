package pl.take.clinic;

import javax.persistence.*;

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
}
