package storage.service;

public interface HashProviderService {
    public String generateHashFromString(String text) throws HashProviderException;
}
