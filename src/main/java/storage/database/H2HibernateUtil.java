package storage.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

public class H2HibernateUtil implements IHibernateUtil{

    @Value("${file.hibernate.h2config}")
    private String hibernateConfig;
    private SessionFactory sessionFactory;
    private StandardServiceRegistry registry;

    @Override
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder()
                        .configure(hibernateConfig)
                        .build();

                Configuration cfg = new Configuration();
                cfg.configure(hibernateConfig);
                sessionFactory = cfg.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
