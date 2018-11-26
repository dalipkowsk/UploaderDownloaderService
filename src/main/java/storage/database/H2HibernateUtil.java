package storage.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class H2HibernateUtil implements IHibernateUtil{
    private SessionFactory sessionFactory;
    private final String hibernateConfig = "hibernateH2.cfg.xml";
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
