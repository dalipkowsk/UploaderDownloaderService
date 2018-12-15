package storage.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    FileLinkDTO upload(MultipartFile file,
                String title,
                String author,
                boolean isPrivate,
                String password,
                HttpServletRequest request) throws FileUploadException, HashProviderException;
}
