package controller;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import storage.Application;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class FileControllerUploadRealFile {

    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file;

    @Value("${file.defaultStorageLocation}")
    private String pathToSaveFile;

    private MockMultipartFile loadFileIntoMock(){
        File initialFile = new File("src/test/resources/test.pdf");
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return new MockMultipartFile("file", "test.pdf", "multipart/form-data", targetStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MockMultipartFile("error","error","multipart/form-data","error".getBytes());
    }

    @Test
    public void shouldSaveFile() throws Exception {
        file = loadFileIntoMock();
        this.mockMvc.perform(fileUpload("/storage/file").file(file))
                    .andExpect(status().isOk());

        assertTrue(Files.exists(Paths.get((pathToSaveFile))));
    }

    @AfterAll
    public void clean() throws IOException {
        Files.deleteIfExists(Paths.get(pathToSaveFile + "test.pdf"));
    }

}
