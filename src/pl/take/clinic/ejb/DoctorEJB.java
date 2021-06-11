package pl.take.clinic.ejb;

import pl.take.clinic.model.Doctor;
import pl.take.clinic.model.Visit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DoctorEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public void create(Doctor doctor) {
        System.out.println("Creating a doctor!");
        entityManager.persist(doctor);
    }

    public Doctor find(long id) {
        return entityManager.find(Doctor.class, id);
    }

    public List<Doctor> get(String speciality, String firstName, String lastName) {
        TypedQuery<Doctor> query = entityManager.createQuery(
                "select d from Doctor d " +
                        "where (:specialityAvailable = false or d.speciality = :speciality) " +
                        "and (:firstNameAvailable = false or d.firstName = :firstName) " +
                        "and (:lastNameAvailable = false or d.lastName = :lastName)",
                Doctor.class
        );

        query.setParameter("specialityAvailable", speciality != null);
        query.setParameter("speciality", speciality);

        query.setParameter("firstNameAvailable", firstName != null);
        query.setParameter("firstName", firstName);

        query.setParameter("lastNameAvailable", lastName != null);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    public List<Visit> getVisits(long id) {
        return new ArrayList<>(find(id).getVisits());
    }

    public void update(Doctor doctor) {
        doctor = entityManager.merge(doctor);
    }
}
