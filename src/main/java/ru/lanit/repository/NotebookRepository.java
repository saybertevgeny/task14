package ru.lanit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.lanit.entity.Notebook;
import ru.lanit.exception.NoEntityException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook,Integer> {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void update(Notebook notebook) {
//        entityManager.merge(notebook);
//        entityManager.flush();
//    }
//
//    @Transactional
//    public void add(Notebook notebook) {
//        entityManager.persist(notebook);
//        entityManager.flush();
//    }
//
//    public List<Notebook> findAll(){
//        CriteriaQuery<Notebook> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Notebook.class);
//        Root<Notebook> rootEntry = criteriaQuery.from(Notebook.class);
//        criteriaQuery.select(rootEntry);
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }
//
//    public Notebook findById(int id){
//        return this.entityManager.find(Notebook.class,id);
//    }
//
//    @Transactional
//    public void delete(Notebook notebook) throws NoEntityException{
//        entityManager.remove(entityManager.contains(notebook) ? notebook : entityManager.merge(notebook));
//    }
}
