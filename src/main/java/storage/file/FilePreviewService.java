package storage.file;

public interface FilePreviewService {
    FileInfoDTO getFilePreview(String fileHash);
}
