package ru.lanit.repository;

import org.springframework.stereotype.Component;
import ru.lanit.entity.Notebook;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class NotebookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void update(Notebook notebook) {
        entityManager.merge(notebook);
        entityManager.flush();
    }

    @Transactional
    public void add(Notebook notebook) {
        entityManager.persist(notebook);
        entityManager.flush();
    }

    public List<Notebook> findAll(){
        CriteriaQuery<Notebook> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Notebook.class);
        Root<Notebook> rootEntry = criteriaQuery.from(Notebook.class);
        criteriaQuery.select(rootEntry);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Notebook findById(int id){
        return this.entityManager.find(Notebook.class,id);
    }
}
