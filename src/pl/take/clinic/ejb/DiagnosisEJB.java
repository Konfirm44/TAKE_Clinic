package pl.take.clinic.ejb;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Diagnosis;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DiagnosisEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public List<Diagnosis> getAll() {
        TypedQuery<Diagnosis> query = entityManager.createQuery("select d from Diagnosis d", Diagnosis.class);

        return query.getResultList();
    }

    public Diagnosis getById(Long id) {
        return entityManager.find(Diagnosis.class, id);
    }

    public CreationStatus create(String note, Long diseaseId, Long visitId) {
        try {
            String sqlQuery = "INSERT INTO diagnosis (note, disease_id, visit_id) VALUES (?, ?, ?);";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, note)
                    .setParameter(2, diseaseId)
                    .setParameter(3, visitId)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }

    public CreationStatus update(Long id, String note, Long diseaseId, Long visitId) {
        try {
            String sqlQuery = "UPDATE diagnosis SET note=?, disease_id=?, visit_id=? WHERE id=?;";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, note)
                    .setParameter(2, diseaseId)
                    .setParameter(3, visitId)
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

    public CreationStatus createPersist(Diagnosis diagnosis) {
        try {
            entityManager.persist(diagnosis);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }

    public CreationStatus updateMerge(Diagnosis diagnosis) {
        try {
            entityManager.merge(diagnosis);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }
}
