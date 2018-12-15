package storage.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import storage.controller.FileController;
import storage.database.FileDataNotFoundInDBException;
import storage.service.*;

@ControllerAdvice(basePackageClasses = FileController.class)
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(FileExceptionHandler.class);

    @ExceptionHandler(value = FileDownloadException.class)
    protected ResponseEntity downloadFileExceptionHandler(FileDownloadException exception) {

        log.error(exception.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileUploadException.class)
    protected ResponseEntity fileUploadWithParamsHandler(FileUploadException exception) {

        log.error(exception.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HashProviderException.class)
    protected ResponseEntity hashProviderExceptionHander(HashProviderException exception) {

        log.error( exception.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    protected ResponseEntity invalidPasswordException(InvalidPasswordException exception) {

        log.error("Invalid Password");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = PasswordRequiredException.class)
    protected ResponseEntity passwordRequiredException(PasswordRequiredException exception) {

        log.error("Password required");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = FileDataNotFoundInDBException.class)
    protected ResponseEntity passwordRequiredException(FileDataNotFoundInDBException exception) {

        log.error("Database returns 0 rows");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
