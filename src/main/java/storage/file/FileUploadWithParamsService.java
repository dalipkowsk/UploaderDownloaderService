package storage.file;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileUploadWithParamsService {

    void uploadWithParams(MultipartFile file,
                          String title,
                          String author,
                          HttpServletRequest request) throws IOException;
}
