package storage.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import storage.database.FileData;
import storage.database.FileDataDAO;

@Service
public class FilePreviewServiceImpl implements FilePreviewService{

    @Autowired
    private FileDataDAO fileDataDAO;

    @Value("http://localhost:8080")
    private String serverAddress;

    public FileInfoDTO getFilePreview(String fileHash){

        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        FileData fileData = fileDataDAO.getFileDataByHash32(fileHash);
        fileInfoDTO.setDownloadCount(fileData.getDownloadCount());
        fileInfoDTO.setDownloadLink( serverAddress + "/download/" + fileHash);
        fileInfoDTO.setEndUploadTimestamp(fileData.getEndUploadTimestamp());
        fileInfoDTO.setExpiryTimestamp(fileData.getExpiryTimestamp());
        fileInfoDTO.setFileExpired(fileData.isFileExpired());
        fileInfoDTO.setFileName(fileData.getFileName());
        fileInfoDTO.setPrivate(fileData.isPrivate());
        fileInfoDTO.setFileSizeB(fileData.getFileSizeB());
        fileInfoDTO.setUploaderName(fileData.getUploaderName());
        fileInfoDTO.setTitle(fileData.getTitle());
        fileInfoDTO.setFileType(fileData.getFileType());
        fileInfoDTO.setStartUploadTimestamp(fileData.getStartUploadTimestamp());

        return fileInfoDTO;
    }
}
