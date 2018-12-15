package storage.service;

import java.util.Date;

public class FileInfoDTO {

    private String title;
    private String fileName;
    private String fileType;
    private String downloadLink;
    private Date startUploadTimestamp;
    private Date endUploadTimestamp;
    private int downloadCount;
    private String uploaderName;
    private Date expiryTimestamp;
    private long fileSizeB;
    private boolean isPrivate;
    private boolean isFileExpired;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public Date getStartUploadTimestamp() {
        return startUploadTimestamp;
    }

    public void setStartUploadTimestamp(Date startUploadTimestamp) {
        this.startUploadTimestamp = startUploadTimestamp;
    }

    public Date getEndUploadTimestamp() {
        return endUploadTimestamp;
    }

    public void setEndUploadTimestamp(Date endUploadTimestamp) {
        this.endUploadTimestamp = endUploadTimestamp;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public Date getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(Date expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }

    public long getFileSizeB() {
        return fileSizeB;
    }

    public void setFileSizeB(long fileSizeB) {
        this.fileSizeB = fileSizeB;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isFileExpired() {
        return isFileExpired;
    }

    public void setFileExpired(boolean fileExpired) {
        isFileExpired = fileExpired;
    }
}
