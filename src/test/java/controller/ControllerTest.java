package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import storage.Application;
import storage.database.FileDataDAO;
import storage.file.FileInfoDTO;
import storage.file.FileLinkDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("develop")
@AutoConfigureMockMvc
public class ControllerTest {
    private static final Logger log = LogManager.getLogger(DatabaseHibernateTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testServiceUploadDownloadWithNoPassword() throws Exception {

        //PERFORM FILE
        MockMultipartFile testFile = new MockMultipartFile("file",
                "yasuo.txt",
                "text/plain",
                "Litwo ojczyzno moja!".getBytes());

        //UPLOAD TEST FILE WITH FORM INFO
        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(testFile)
                .param("title", "Hasagi!")
                .param("author", "Yasuo")
                .param("isPrivate", "false")
                .param("password", ""))
                .andExpect( status().is(200) )
                .andReturn();

        String content = resultActions.getResponse().getContentAsString();
        log.info( content );

        //NOW GO TO PREVIEW PAGE
        ObjectMapper mapper = new ObjectMapper();
        FileLinkDTO fileLinkDTO = mapper.readValue(content, FileLinkDTO.class);

        MvcResult result = mockMvc
                .perform(get( fileLinkDTO.getFullLink() )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String previewResult = result.getResponse().getContentAsString();
        log.info(previewResult);

        //NOW CHECK DATA ON PREVIEW PAGE
        ObjectMapper previewObject = new ObjectMapper();
        FileInfoDTO filePreviewDTO = previewObject.readValue(previewResult, FileInfoDTO.class);

        Assert.assertEquals("Hasagi!", filePreviewDTO.getTitle());
        Assert.assertEquals("Yasuo", filePreviewDTO.getUploaderName());
        Assert.assertEquals(false, filePreviewDTO.isPrivate());

        //NOW TRY DOWNLOAD FILE
        MvcResult downloadFile = mockMvc.perform( get(filePreviewDTO.getDownloadLink() )
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                        .andExpect( status().isOk() )
                        .andReturn();

        String downloadedFileContent = downloadFile.getResponse().getContentAsString();
        log.info( "File content: " + downloadedFileContent );

        Assert.assertEquals("Litwo ojczyzno moja!", downloadedFileContent );

        //NOW CHECK THAT FILE NAME IS SAME AS IN DATABASE
        Assert.assertEquals( downloadFile.getResponse().getHeader("content-disposition"),
                "attachment; filename=" + filePreviewDTO.getFileName() );
    }

    @Test
    public void testServiceUploadDownloadWithPassword() throws Exception {
        //PERFORM FILE
        MockMultipartFile testFile = new MockMultipartFile("file",
                "neeko.txt",
                "text/plain",
                "Really secured message!".getBytes());

        //UPLOAD TEST FILE WITH FORM INFO
        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(testFile)
                .param("title", "Secured Message To You")
                .param("author", "Neeko")
                .param("isPrivate", "true")
                .param("password", "qwerty"))
                .andExpect( status().is(200) )
                .andReturn();

        String content = resultActions.getResponse().getContentAsString();
        log.info( content );

        //NOW GO TO PREVIEW PAGE
        ObjectMapper mapper = new ObjectMapper();
        FileLinkDTO fileLinkDTO = mapper.readValue(content, FileLinkDTO.class);

        MvcResult result = mockMvc
                .perform(get( fileLinkDTO.getFullLink() )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String previewResult = result.getResponse().getContentAsString();
        log.info(previewResult);

        //PARSE DATA FROM PREVIEW PAGE
        ObjectMapper previewObject = new ObjectMapper();
        FileInfoDTO filePreviewDTO = previewObject.readValue(previewResult, FileInfoDTO.class);

        //NOW TRY DOWNLOAD FILE
        MvcResult downloadFile = mockMvc.perform( get(filePreviewDTO.getDownloadLink()+"/qwerty")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect( status().isOk() )
                .andReturn();

        String securedMessage = downloadFile.getResponse().getContentAsString();
        log.info( securedMessage );
        Assert.assertEquals("Really secured message!", securedMessage );
    }

}