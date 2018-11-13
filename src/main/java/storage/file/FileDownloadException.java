package storage.file;

import java.io.FileNotFoundException;

public class FileDownloadException extends FileNotFoundException {
    FileDownloadException(FileNotFoundException message){
    }
}
