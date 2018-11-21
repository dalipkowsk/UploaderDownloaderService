package storage.database;

import javax.persistence.*;

@Entity(name = "filedata")
public class FileData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "file_Sequence")
    @SequenceGenerator(name = "file_Sequence", sequenceName = "FILE_SEQ")
    private long id;

    private String title;
    private String ftpAddress;
    private String path;
    private String hash32;
    private String fileType;
    private String startUploadTimestamp;
    private String endUploadTimestamp;
    private int downloadCount;
    private String uploaderName;
    private String expiryTimestamp;
    private String uploaderIPAddress;
    private int fileSizeKB;
    private boolean isPrivate;
    private String passwordHash;
    private boolean isFileExpired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFtpAddress() {
        return ftpAddress;
    }

    public void setFtpAddress(String ftpAddress) {
        this.ftpAddress = ftpAddress;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHash32() {
        return hash32;
    }

    public void setHash32(String hash32) {
        this.hash32 = hash32;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStartUploadTimestamp() {
        return startUploadTimestamp;
    }

    public void setStartUploadTimestamp(String startUploadTimestamp) {
        this.startUploadTimestamp = startUploadTimestamp;
    }

    public String getEndUploadTimestamp() {
        return endUploadTimestamp;
    }

    public void setEndUploadTimestamp(String endUploadTimestamp) {
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

    public String getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(String expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }

    public String getUploaderIPAddress() {
        return uploaderIPAddress;
    }

    public void setUploaderIPAddress(String uploaderIPAddress) {
        this.uploaderIPAddress = uploaderIPAddress;
    }

    public int getFileSizeKB() {
        return fileSizeKB;
    }

    public void setFileSizeKB(int fileSizeKB) {
        this.fileSizeKB = fileSizeKB;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isFileExpired() {
        return isFileExpired;
    }

    public void setFileExpired(boolean fileExpired) {
        isFileExpired = fileExpired;
    }

    public FileData() {  }
}
