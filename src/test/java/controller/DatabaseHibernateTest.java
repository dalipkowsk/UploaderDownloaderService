package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import storage.Application;
import storage.database.FileData;
import storage.database.FileDataDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DatabaseHibernateTest {

    private static final Logger log = LogManager.getLogger(DatabaseHibernateTest.class);

    @Autowired
    private FileDataDAO fileDataDAO;

    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file = new MockMultipartFile(
            "file",
            "file",
            "multipart/form-data",
            "test".getBytes()
    );

    @Value("${service.directory}")
    private String pathToSaveFile;

    @Test
    public void testHibernateInsert() {
        FileData testFileData = new FileData();
        testFileData.setTitle("TestFileTest");
        fileDataDAO.save(testFileData);
        fileDataDAO.deleteById(1);
    }

    @Test
    public void testHibernateSelectAll() {
        FileData testFileData = new FileData();
        testFileData.setTitle("TestFileTest");
        FileData testFileData2 = new FileData();
        testFileData2.setTitle("TestFileTest2");
        FileData testFileData3 = new FileData();
        testFileData3.setTitle("TestFileTest3");
        fileDataDAO.save(testFileData);
        fileDataDAO.save(testFileData2);
        fileDataDAO.save(testFileData3);

        List<FileData> dataSet = fileDataDAO.list();
        for( FileData c : dataSet ) {
            log.info(c.toString());
        }
    }

    @AfterAll
    public void clean() throws IOException {
        Files.deleteIfExists(Paths.get(pathToSaveFile + "file"));
    }

}