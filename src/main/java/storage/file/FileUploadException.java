package storage.file;

import java.io.IOException;

public class FileUploadException extends IOException {

    FileUploadException(IOException exception){

        super("Error while upload file");
    }
}
