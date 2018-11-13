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

    @Value("${file.directory}")
    private String fileDirectory;

    @Override
    public InputStreamResource downloadFile(String fileName) throws FileDownloadException {
        Path filePath = Paths.get(fileDirectory + fileName);
        File file = new File( filePath.toString() );

        System.out.println( fileDirectory );
        System.out.println( fileName );
        System.out.println( filePath.toString() );

        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new FileDownloadException( e );
        }

        return inputStreamResource;
    }
}
