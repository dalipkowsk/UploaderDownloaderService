package controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import storage.Application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class FileControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file = new MockMultipartFile("file", "file", "multipart/form-data", "test".getBytes());

    @Value("${file.directory}")
    private String pathToSaveFile;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldSaveFile() throws Exception {

        this.mockMvc.perform(fileUpload("/storage/file").file(file))
                    .andExpect(status().isOk());

        assertTrue(Files.exists(Paths.get((pathToSaveFile))));
    }

    @Test
    public void shouldGetFile() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.MULTIPART_FORM_DATA));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/download/file",
                HttpMethod.GET, entity, String.class, "1");

        Assert.assertEquals("test", response.getBody());
    }

    @AfterAll
    public void clean() throws IOException {
        Files.deleteIfExists(Paths.get(pathToSaveFile + file.getOriginalFilename()));
    }

}