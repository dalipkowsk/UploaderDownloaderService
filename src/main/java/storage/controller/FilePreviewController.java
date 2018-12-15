package storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import storage.database.FileDataNotFoundInDBException;
import storage.service.FileInfoDTO;
import storage.service.FilePreviewService;

@RestController(value = "/storage/fileinfo")
public class FilePreviewController {

    @Autowired
    FilePreviewService filePreviewService;

    @GetMapping(value = "/preview/{fileHash}")
    public ResponseEntity previewFile(@PathVariable String fileHash) throws FileDataNotFoundInDBException {

        FileInfoDTO fileInfoDTO = filePreviewService.getFilePreview(fileHash);
        return new ResponseEntity(fileInfoDTO, HttpStatus.OK);
    }

}
