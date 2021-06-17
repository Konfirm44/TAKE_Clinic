package pl.take.clinic.ejb;

import pl.take.clinic.model.CreationStatus;
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

    public void createPersist(Patient patient) {
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

    public void updateMerge(Patient patient) {
        patient = entityManager.merge(patient);
    }

    public CreationStatus create(String firstName, String lastName, String pesel) {
        try {
            String sqlQuery = "INSERT INTO Patient (firstName, lastName, pesel) VALUES (?, ?, ?);";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, pesel)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }

    public CreationStatus update(Long id, String firstName, String lastName, String pesel) {
        try {
            String sqlQuery = "UPDATE Patient SET firstName=?, lastName=?, pesel=? WHERE id=?;";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, firstName)
                    .setParameter(2, lastName)
                    .setParameter(3, pesel)
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
