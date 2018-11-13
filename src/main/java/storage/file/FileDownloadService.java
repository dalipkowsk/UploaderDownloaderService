package storage.file;

import org.springframework.core.io.InputStreamResource;

public interface FileDownloadService {
    InputStreamResource downloadFile(String fileName) throws FileDownloadException;
}
