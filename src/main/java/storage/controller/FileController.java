package storage.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import storage.file.FileDownloadException;
import storage.file.FileDownloadService;
import storage.file.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/download/{fileName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity download( @PathVariable String fileName ) throws FileDownloadException {
        return new ResponseEntity<>(fileDownloadService.downloadFile(fileName), HttpStatus.OK);
    }
}
