package storage.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import storage.controller.FileController;
import storage.file.FileDownloadException;
import storage.file.FileUploadException;

@ControllerAdvice(basePackageClasses = FileController.class)
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(FileExceptionHandler.class);

    @ExceptionHandler(value = FileUploadException.class)
    protected ResponseEntity uploadFileExceptionHandler(FileUploadException exception) {

        log.error(exception.getMessage());

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileDownloadException.class)
    protected ResponseEntity downloadFileExceptionHandler(FileDownloadException exception) {

        log.error(exception.getMessage());

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
