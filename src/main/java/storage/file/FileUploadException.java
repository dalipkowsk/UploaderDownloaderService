package storage.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class FileUploadException extends IOException {

    private static final Logger log = LogManager.getLogger(FileUploadException.class);

    FileUploadException(String message){
        log.error(message);
    }
}
