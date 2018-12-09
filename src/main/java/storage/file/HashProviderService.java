package storage.file;

public interface HashProviderService {
    public String generateHashFromString(String text) throws HashProviderException;
}
