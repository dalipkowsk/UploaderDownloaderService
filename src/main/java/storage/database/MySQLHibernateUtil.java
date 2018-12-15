package storage.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

public class MySQLHibernateUtil implements IHibernateUtil{

    @Value("${file.hibernate.mysqlconfig}")
    private String hibernateConfig;
    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create SessionFactory
                Configuration cfg = new Configuration();
                cfg.configure(hibernateConfig);
                sessionFactory = cfg.buildSessionFactory();

                // Create registry
                registry = new StandardServiceRegistryBuilder()
                        .configure(hibernateConfig)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }


    public void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
