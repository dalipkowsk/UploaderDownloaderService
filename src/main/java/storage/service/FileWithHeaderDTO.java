package storage.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

public class FileWithHeaderDTO {
    private InputStreamResource fileContent;
    private HttpHeaders httpHeaders;

    public InputStreamResource getFileContent() {
        return fileContent;
    }

    public void setFileContent(InputStreamResource fileContent) {
        this.fileContent = fileContent;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
