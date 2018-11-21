package storage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {
    @Override
    <S extends FileData> S save(S s);
    List<FileData> findById(long id);
}
