package storage.service;

import java.io.FileNotFoundException;

public class FileDownloadException extends FileNotFoundException {
    FileDownloadException(FileNotFoundException message){
    }
}
