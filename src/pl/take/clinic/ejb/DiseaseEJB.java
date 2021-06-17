package pl.take.clinic.ejb;

import pl.take.clinic.model.CreationStatus;
import pl.take.clinic.model.Disease;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DiseaseEJB {
    @PersistenceContext(name="clinic")
    EntityManager entityManager;

    public List<Disease> getAll() {
        TypedQuery<Disease> query = entityManager.createQuery("select d from Disease d", Disease.class);

        System.out.println("Listing diseases...");

        return query.getResultList();
    }

    public Disease getById(Integer id) {
        return entityManager.find(Disease.class, id);
    }

    public CreationStatus create(String contagious, String name) {
        try {
            String sqlQuery = "INSERT INTO Disease (contagious, name) VALUES (?, ?);";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, contagious)
                    .setParameter(2, name)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }

    public CreationStatus update(Integer id, String contagious, String name) {
        try {
            String sqlQuery = "UPDATE Disease SET contagious=?, name=? WHERE id=?;";

            int nativeQuery = entityManager.createNativeQuery(sqlQuery)
                    .setParameter(1, contagious)
                    .setParameter(2, name)
                    .setParameter(3, id)
                    .executeUpdate();

            if (nativeQuery == 1) {
                return CreationStatus.Success;
            }
        } catch (Exception err) {
            return CreationStatus.Failed;
        }

        return CreationStatus.Failed;
    }

    public CreationStatus createPersist(Disease disease) {
        try {
            entityManager.persist(disease);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }

    public CreationStatus updateMerge(Disease disease) {
        try {
            entityManager.merge(disease);

            return CreationStatus.Success;
        } catch (Exception error) {
            return CreationStatus.Failed;
        }
    }
}
