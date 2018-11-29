package storage.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import storage.database.FileData;
import storage.database.FileDataDAO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * https://stackoverflow.com/questions/47902959/file-upload-progress-in-spring-boot
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.directory}")
    private String fileDirectory;

    private final FileDataDAO fileDataDAO;

    @Autowired
    public FileUploadServiceImpl(FileDataDAO fileDataDAO) {
        this.fileDataDAO = fileDataDAO;
    }

    @Override
    public void upload(MultipartFile file,
                       String title,
                       String author,
                       HttpServletRequest request) throws IOException {
        FileData fileData = new FileData();

        byte[] fileBytes;

        try {                               //Przez to, że zbyt ogólny jest ten wyjątek, muszę rzucić własny
            fileBytes = file.getBytes();
        } catch (IOException exception) {
            throw new FileUploadException(exception);
        }

        Path filePath = Paths.get(fileDirectory + file.getOriginalFilename());

        try {
            Files.write(filePath, fileBytes);
        } catch (IOException exception) {
            throw new FileUploadException(exception);
        }

        fileData.setTitle(title);
        fileData.setPath(fileDirectory + file.getOriginalFilename());
            //fileData.setHash32();
        fileData.setFileType( FilenameUtils.getExtension(file.getOriginalFilename()) );
            //fileData.setStartUploadTimestamp();
            //fileData.setEndUploadTimestamp();
        fileData.setUploaderName(author);
            //fileData.setExpiryTimestamp();
        fileData.setUploaderIPAddress(request.getRemoteAddr());
        fileData.setFileSizeB(file.getSize());
        fileData.setPrivate(false);
            //fileData.setPasswordHash();
        fileData.setFileExpired(false);

        fileDataDAO.save(fileData);
    }
}
