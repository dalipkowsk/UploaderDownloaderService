package storage.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Value("${file.defaultStorageLocation}")
    private String fileDirectory;

    @Override
    public InputStreamResource downloadFile(String fileName) throws FileNotFoundException {
        Path filePath = Paths.get(fileDirectory + fileName);
        File file = new File( filePath.toString() );

        InputStreamResource inputStreamResource;
        inputStreamResource = new InputStreamResource(new FileInputStream(file));

        return inputStreamResource;
    }
}
