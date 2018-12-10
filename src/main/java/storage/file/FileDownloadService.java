package storage.file;

import org.springframework.core.io.InputStreamResource;

public interface FileDownloadService {
    FileWithHeaderDTO downloadFile(String fileHash) throws FileDownloadException;
}
