package storage.database;

import org.hibernate.SessionFactory;

public interface IHibernateUtil {
    SessionFactory getSessionFactory();
    void shutdown();
}
