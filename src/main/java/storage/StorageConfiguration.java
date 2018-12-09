package storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import storage.database.*;
import storage.file.HashProviderService;
import storage.file.HashSha1Service;

@Configuration
public class StorageConfiguration {

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
    @Primary
    public FileDataDAO returnFileDataDAO(){

        return new FileDataDAOImpl();
    }

    @Bean
    @Primary
    public HashProviderService returnHashSha1Service(){
        return new HashSha1Service();
    }
}
