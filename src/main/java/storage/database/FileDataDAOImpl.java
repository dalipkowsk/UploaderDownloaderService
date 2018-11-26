package storage.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FileDataDAOImpl implements FileDataDAO {

    @Autowired
    IHibernateUtil hibernateUtil;

    @Override
    public void save(FileData file) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(file);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteById(long fileId) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<FileData> delete = criteriaBuilder.createCriteriaDelete(FileData.class);
        Root<FileData> root = delete.from(FileData.class);
        delete.where(criteriaBuilder.in(root.get("id")).value(fileId));
        session.createQuery(delete).executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public List<FileData> list() {
        Session session = hibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FileData> criteriaQuery = criteriaBuilder.createQuery(FileData.class);
        Root<FileData> root = criteriaQuery.from(FileData.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        List<FileData> weatherModelList = query.getResultList();
        return weatherModelList;
    }
}
