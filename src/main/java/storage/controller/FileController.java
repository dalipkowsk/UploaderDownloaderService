package storage.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import storage.Application;
import storage.file.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/download/{fileHash}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity download( @PathVariable String fileHash ) throws FileDownloadException {
        FileWithHeaderDTO fileWithHeaderDTO = fileDownloadService.downloadFile(fileHash);
        return new ResponseEntity<>(fileWithHeaderDTO.getFileContent(),
                fileWithHeaderDTO.getHttpHeaders(),
                HttpStatus.OK);
    }

    @PostMapping(name = "/upload", consumes = "multipart/form-data")
    public ResponseEntity uploadWithParams(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "author") String author,
            @RequestParam(value = "isPrivate") boolean isPrivate,
            @RequestParam(value = "password") String password,
            HttpServletRequest request
    ) throws FileUploadException, HashProviderException {
        FileLinkDTO fileLinkDTO = fileUploadWithParamsService.upload(file,
                title,
                author,
                isPrivate,
                password,
                request);
        return new ResponseEntity(fileLinkDTO, HttpStatus.OK);
    }
}
