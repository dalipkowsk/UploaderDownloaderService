package storage.file;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileUploadService {

    FileLinkDTO upload(MultipartFile file,
                String title,
                String author,
                boolean isPrivate,
                String password,
                HttpServletRequest request) throws FileUploadException, HashProviderException;
}
