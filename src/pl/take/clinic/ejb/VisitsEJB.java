package pl.take.clinic.ejb;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Visit;
import pl.take.clinic.model.VisitStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class VisitsEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public List<Visit> getAll() {
        TypedQuery<Visit> query = entityManager.createQuery("select v from Visit v", Visit.class);

        System.out.println("Listing visits...");

        return query.getResultList();
    }

    public Visit getById(Long id) {
        return entityManager.find(Visit.class, id);
    }

    public CreationStatus create(String note, VisitStatus status, Long doctorId, Long patientId) {
        return CreationStatus.Failed;
    }

    public CreationStatus update(Long id, String note, VisitStatus status, Long doctorId, Long patientId) {
        return CreationStatus.Failed;
    }

    public CreationStatus createPersist(Visit visit) {
        try {
            entityManager.persist(visit);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }

    public CreationStatus updateMerge(Visit visit) {
        try {
            entityManager.merge(visit);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }
}
