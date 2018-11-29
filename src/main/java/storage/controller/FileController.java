package storage.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import storage.Application;
import storage.database.FileData;
import storage.database.FileDataDAO;
import storage.exception.FileExceptionHandler;
import storage.file.FileDownloadException;
import storage.file.FileDownloadService;
import storage.file.FileUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import storage.file.FileUploadWithParamsService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController(value = "/storage/file")
public class FileController {

    private static final Logger log = LogManager.getLogger(Application.class);

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileDownloadService fileDownloadService;

    @Autowired
    private FileUploadWithParamsService fileUploadWithParamsService;

    @Autowired
    private FileDataDAO fileDataDAO;

    public FileController(FileUploadService fileUploadService, FileDownloadService fileDownloadService){
        this.fileUploadService = fileUploadService;
        this.fileDownloadService = fileDownloadService;
    }

    /*@PostMapping(name = "/upload", consumes = "multipart/form-data")
    public ResponseEntity upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        log.info( "heheszki" );
        fileUploadService.uploadFile(file);
        return new ResponseEntity(HttpStatus.OK);
    }*/

    @GetMapping(value = "/download/{fileName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity download( @PathVariable String fileName ) throws FileDownloadException {
        return new ResponseEntity<>(fileDownloadService.downloadFile(fileName), HttpStatus.OK);
    }

    @PostMapping(name = "/uploadWithParams", consumes = "multipart/form-data")
    public ResponseEntity uploadWithParams(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "author") String author,
            HttpServletRequest request
    ) throws IOException {
        fileUploadWithParamsService.uploadWithParams(file, title, author, request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
