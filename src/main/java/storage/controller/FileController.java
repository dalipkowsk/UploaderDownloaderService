package storage.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import storage.file.FileDownloadService;
import storage.file.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController(value = "/storage/file")
public class FileController {

    private FileUploadService fileUploadService;
    private FileDownloadService fileDownloadService;

    public FileController(FileUploadService fileUploadService, FileDownloadService fileDownloadService){
        this.fileUploadService = fileUploadService;
        this.fileDownloadService = fileDownloadService;
    }

    @PostMapping(name = "/upload", consumes = "multipart/form-data")
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{item}", method = RequestMethod.GET)
    public ResponseEntity download( @PathVariable String item ) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType( MediaType.MULTIPART_FORM_DATA );
            responseHeaders.setContentDispositionFormData("attachment", item);
            return new ResponseEntity<>(fileDownloadService.downloadFile(item), responseHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}