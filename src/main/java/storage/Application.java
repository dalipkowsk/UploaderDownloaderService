package storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import storage.database.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("develop")
    public IHibernateUtil returnMySQLHibernateUtil() {

        return new MySQLHibernateUtil();
    }

    @Bean
    @Profile("test")
    public IHibernateUtil returnH2HibernateUtil() {

        return new H2HibernateUtil();
    }

    @Bean
    public FileDataDAO returnFileDAO(){

        return new FileDataDAOImpl();
    }
}
