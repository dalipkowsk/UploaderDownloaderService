package storage.file;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileUploadService {

    void upload(MultipartFile file,
                String title,
                String author,
                HttpServletRequest request) throws IOException;
}
