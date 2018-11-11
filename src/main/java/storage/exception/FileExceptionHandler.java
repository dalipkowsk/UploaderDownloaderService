package storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import storage.controller.FileController;
import storage.file.FileUploadException;

@ControllerAdvice(basePackageClasses = FileController.class)
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = FileUploadException.class)
    protected ResponseEntity uploadFileExceptionHandler(FileUploadException ex){

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
