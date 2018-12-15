package storage.service;

import storage.database.FileDataNotFoundInDBException;

import java.util.Optional;

public interface FileDownloadService {
    FileWithHeaderDTO downloadFile(String fileHash, Optional<String> password)
            throws FileDownloadException, HashProviderException, InvalidPasswordException, PasswordRequiredException, FileDataNotFoundInDBException;
}
