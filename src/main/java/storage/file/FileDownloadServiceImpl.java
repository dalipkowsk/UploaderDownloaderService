package storage.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Value("${file.defaultStorageLocation}")
    private String fileDirectory;

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String fileName) {
        Path filePath = Paths.get(fileDirectory + fileName);

        File file = new File( filePath.toString() );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType( MediaType.MULTIPART_FORM_DATA );
        responseHeaders.setContentLength( file.length() );
        responseHeaders.setContentDispositionFormData("attachment", fileName);

        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inputStreamResource, responseHeaders, HttpStatus.OK);
    }
}
