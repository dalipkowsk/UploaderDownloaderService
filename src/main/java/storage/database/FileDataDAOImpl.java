package storage.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

/**
 * https://www.journaldev.com/3524/spring-hibernate-integration-example-tutorial
 */
public class FileDataDAOImpl implements FileDataDAO {



    @Override
    public void save(FileData file) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(file);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteById(long file) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        //Query deleteFile = session.createQuery("DELETE FROM file f WHERE f.id = :id");

    }

    @Override
    public List<FileData> list() {
        return null;
    }
}
