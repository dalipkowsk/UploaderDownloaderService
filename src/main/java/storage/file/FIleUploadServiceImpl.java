package storage.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FIleUploadServiceImpl implements FileUploadService {

    @Value("${file.defaultStorageLocation}")
    private String fileDirectory;

    @Override
    public void uploadFile(MultipartFile file) throws FileUploadException {

        byte[] fileBytes;

        try {                               //Przez to, że zbyt ogólny jest ten wyjątek, muszę rzucić własny
            fileBytes = file.getBytes();

        } catch (IOException e) {
            throw new FileUploadException(e.getMessage());
        }

        Path filePath = Paths.get(fileDirectory + file.getOriginalFilename());

        try {

            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            throw new FileUploadException(e.getMessage());
        }
    }
}
