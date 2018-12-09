package storage.database;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "filedata")
public class FileData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "file_Sequence")
    @SequenceGenerator(name = "file_Sequence", sequenceName = "FILE_SEQ")
    private long id;

    private String title;
    private String fileName;
    private String ftpAddress;
    private String path;
    private String hash32;
    private String fileType;
    private Date startUploadTimestamp;
    private Date endUploadTimestamp;
    private int downloadCount;
    private String uploaderName;
    private Date expiryTimestamp;
    private String uploaderIPAddress;
    private long fileSizeB;
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

    public String getUploaderIPAddress() {
        return uploaderIPAddress;
    }

    public void setUploaderIPAddress(String uploaderIPAddress) {
        this.uploaderIPAddress = uploaderIPAddress;
    }

    public long getFileSizeB() {
        return fileSizeB;
    }

    public void setFileSizeB(long fileSizeKB) {
        this.fileSizeB = fileSizeKB;
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

    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", ftpAddress='" + ftpAddress + '\'' +
                ", path='" + path + '\'' +
                ", hash32='" + hash32 + '\'' +
                ", fileType='" + fileType + '\'' +
                ", startUploadTimestamp=" + startUploadTimestamp +
                ", endUploadTimestamp=" + endUploadTimestamp +
                ", downloadCount=" + downloadCount +
                ", uploaderName='" + uploaderName + '\'' +
                ", expiryTimestamp=" + expiryTimestamp +
                ", uploaderIPAddress='" + uploaderIPAddress + '\'' +
                ", fileSizeB=" + fileSizeB +
                ", isPrivate=" + isPrivate +
                ", passwordHash='" + passwordHash + '\'' +
                ", isFileExpired=" + isFileExpired +
                '}';
    }

    public FileData() {  }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
