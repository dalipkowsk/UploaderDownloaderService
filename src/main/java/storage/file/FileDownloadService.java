package storage.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface FileDownloadService {
    ResponseEntity<InputStreamResource> downloadFile(String fileName);
}
