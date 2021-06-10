package pl.take.clinic.ejb;

import pl.take.clinic.model.Patient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PatientEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public void create(Patient patient) {
        System.out.println("Creating a patient!");
        entityManager.persist(patient);
    }

    public Patient findByPesel(String pesel) {
        TypedQuery<Patient> q = entityManager.createQuery("select p from Patient p where p.pesel = :pesel", Patient.class);
        q.setParameter("pesel", pesel);
        return q.getSingleResult();
    }

    public Patient find(long id) {
        return entityManager.find(Patient.class, id);
    }

    public List<Patient> get() {
        return entityManager.createQuery("select p from Patient p", Patient.class)
                            .getResultList();
    }

    public void update(Patient patient) {
        patient = entityManager.merge(patient);
    }

    public void delete(long id) {
        Patient patient = find(id);
        entityManager.remove(patient);
    }
}
