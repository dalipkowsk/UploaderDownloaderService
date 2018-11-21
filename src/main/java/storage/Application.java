package storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import storage.database.FileDataDAO;
import storage.database.FileDataDAOImpl;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FileDataDAO returnFileDAO(){
        return new FileDataDAOImpl();
    }

}
