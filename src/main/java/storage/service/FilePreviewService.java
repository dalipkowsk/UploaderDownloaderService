package storage.service;

import storage.database.FileDataNotFoundInDBException;

public interface FilePreviewService {
    FileInfoDTO getFilePreview(String fileHash) throws FileDataNotFoundInDBException;
}
