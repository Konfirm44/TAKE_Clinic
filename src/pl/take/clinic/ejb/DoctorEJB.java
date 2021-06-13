package pl.take.clinic.ejb;

import pl.take.clinic.model.CreationStatus;
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

    public void createPersist(Doctor doctor) {
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

    public void updateMerge(Doctor doctor) {
        doctor = entityManager.merge(doctor);
    }

    public CreationStatus create(String firstName, String lastName, String speciality) {
        try {
            String sqlQuery = "INSERT INTO Doctor (firstName, lastName, speciality) VALUES (?, ?, ?);";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, speciality)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }

    public CreationStatus update(Long id, String firstName, String lastName, String speciality) {
        try {
            String sqlQuery = "UPDATE Doctor SET firstName=?, lastName=?, speciality=? WHERE id=?;";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, speciality)
                    .setParameter(4, id)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }
}
