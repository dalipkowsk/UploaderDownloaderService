package storage.controller;

import storage.file.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController(value = "/storage/file")
public class FileController {

    private FileUploadService fileUploadService;

    public FileController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(name = "/upload", consumes = "multipart/form-data")
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile file) throws IOException {

        fileUploadService.uploadFile(file);

        return new ResponseEntity(HttpStatus.OK);
    }
}