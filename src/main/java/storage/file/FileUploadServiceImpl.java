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
import java.util.Date;

/**
 * https://stackoverflow.com/questions/47902959/file-upload-progress-in-spring-boot
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.directory}")
    private String fileDirectory;

    @Value("http://localhost:8080")
    private String serverAddress;

    private final FileDataDAO fileDataDAO;

    @Autowired
    public FileUploadServiceImpl(FileDataDAO fileDataDAO) {
        this.fileDataDAO = fileDataDAO;
    }

    @Autowired
    private HashProviderService hashProviderService;

    @Override
    public FileLinkDTO upload(MultipartFile file,
                       String title,
                       String author,
                       boolean isPrivate,
                       String password,
                       HttpServletRequest request) throws FileUploadException, HashProviderException {

        FileData fileData = new FileData();
        Date date = new Date();
        String fileHash = hashProviderService.generateHashFromString( date.toString() );

        byte[] fileBytes;

        try {                               //Przez to, że zbyt ogólny jest ten wyjątek, muszę rzucić własny
            fileBytes = file.getBytes();
        } catch (IOException exception) {
            throw new FileUploadException(exception);
        }

        Path filePath = Paths.get(fileDirectory + fileHash);

        try {
            Files.write(filePath, fileBytes);
        } catch (IOException exception) {
            throw new FileUploadException(exception);
        }

        fileData.setTitle(title);
        fileData.setPath( filePath.toString() );
        fileData.setFileName( file.getOriginalFilename() );
        fileData.setHash32(fileHash);
        fileData.setFileType( FilenameUtils.getExtension(file.getOriginalFilename()) );
        fileData.setStartUploadTimestamp( date );
            //fileData.setEndUploadTimestamp();
        fileData.setUploaderName(author);
            //fileData.setExpiryTimestamp();
        fileData.setUploaderIPAddress(request.getRemoteAddr());
        fileData.setFileSizeB(file.getSize());
        fileData.setPrivate(isPrivate);

        if( isPrivate ) {
            String passwordHash = hashProviderService.generateHashFromString( password );
            fileData.setPasswordHash( passwordHash );
        }
        fileData.setFileExpired(false);
        fileDataDAO.save(fileData);

        FileLinkDTO fileLinkDTO = new FileLinkDTO();
        fileLinkDTO.setFullLink( serverAddress + "/preview/" + fileHash );
        return fileLinkDTO;
    }
}
