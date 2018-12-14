package storage.file;

import org.springframework.core.io.InputStreamResource;

import java.util.Optional;

public interface FileDownloadService {
    FileWithHeaderDTO downloadFile(String fileHash, Optional<String> password)
            throws FileDownloadException, HashProviderException;
}
