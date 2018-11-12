package storage.file;

import org.springframework.core.io.InputStreamResource;
import java.io.FileNotFoundException;

public interface FileDownloadService {
    InputStreamResource downloadFile(String fileName) throws FileNotFoundException;
}
