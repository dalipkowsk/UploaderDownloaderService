package storage.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import storage.database.FileData;
import storage.database.FileDataDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Value("${file.directory}")
    private String fileDirectory;

    @Autowired
    FileDataDAO fileDataDAO;

    @Override
    public FileWithHeaderDTO downloadFile(String fileHash) throws FileDownloadException {

        FileData fileData = fileDataDAO.getFileDataByHash32(fileHash);
        Path filePath = Paths.get(fileData.getPath());
        File hashedFile = new File( filePath.toString() );


        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(hashedFile));
        } catch (FileNotFoundException e) {
            throw new FileDownloadException( e );
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition",
                "attachment; filename=" + fileData.getFileName() );
        FileWithHeaderDTO fileWithHeaderDTO = new FileWithHeaderDTO();
        fileWithHeaderDTO.setFileContent(inputStreamResource);
        fileWithHeaderDTO.setHttpHeaders(responseHeaders);

        return fileWithHeaderDTO;
    }
}
