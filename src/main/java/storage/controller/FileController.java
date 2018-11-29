package storage.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import storage.Application;
import storage.file.FileDownloadException;
import storage.file.FileDownloadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import storage.file.FileUploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController(value = "/storage/file")
public class FileController {

    private static final Logger log = LogManager.getLogger(Application.class);
    private final FileDownloadService fileDownloadService;
    private final FileUploadService fileUploadWithParamsService;

    @Autowired
    public FileController(FileDownloadService fileDownloadService, FileUploadService fileUploadWithParamsService) {
        this.fileDownloadService = fileDownloadService;
        this.fileUploadWithParamsService = fileUploadWithParamsService;
    }

    @GetMapping(value = "/download/{fileName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity download( @PathVariable String fileName ) throws FileDownloadException {
        return new ResponseEntity<>(fileDownloadService.downloadFile(fileName), HttpStatus.OK);
    }

    @PostMapping(name = "/upload", consumes = "multipart/form-data")
    public ResponseEntity uploadWithParams(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "author") String author,
            HttpServletRequest request
    ) throws IOException {
        fileUploadWithParamsService.upload(file, title, author, request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
