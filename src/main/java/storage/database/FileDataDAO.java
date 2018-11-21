package storage.database;

import java.util.List;

public interface FileDataDAO {
    void save(FileData file);
    void deleteById(long id);
    List<FileData> list();
}
