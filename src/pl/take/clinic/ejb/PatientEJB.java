package pl.take.clinic.ejb;

import pl.take.clinic.model.Patient;
import pl.take.clinic.model.Visit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PatientEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public void create(Patient patient) {
        System.out.println("Creating a patient!");
        entityManager.persist(patient);
    }

    public Patient find(long id) {
        return entityManager.find(Patient.class, id);
    }

    public List<Patient> get(String pesel, String firstName, String lastName) {
        TypedQuery<Patient> query = entityManager.createQuery(
                "select p from Patient p " +
                        "where (:peselAvailable = false or p.pesel = :pesel) " +
                        "and (:firstNameAvailable = false or p.firstName = :firstName) " +
                        "and (:lastNameAvailable = false or p.lastName = :lastName)",
                Patient.class
        );

        query.setParameter("peselAvailable", pesel != null);
        query.setParameter("pesel", pesel);

        query.setParameter("firstNameAvailable", firstName != null);
        query.setParameter("firstName", firstName);

        query.setParameter("lastNameAvailable", lastName != null);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    public List<Visit> getVisits(long id) {
        return new ArrayList<>(find(id).getVisits());
    }

    public void update(Patient patient) {
        patient = entityManager.merge(patient);
    }

    public void delete(long id) {
        Patient patient = find(id);
        entityManager.remove(patient);
    }
}
