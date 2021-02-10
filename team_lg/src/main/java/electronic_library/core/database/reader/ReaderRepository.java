package electronic_library.core.database.reader;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository {

    void saveReader(Reader reader);

    boolean deleteReader(Reader reader);

    boolean deleteReaderById(Long id);

    boolean deleteReaderByFirstName(String readerFirstName);

    boolean deleteReaderByLastName(String readerLastName);

    Optional<Reader> findReaderById(Long id);

    List<Reader> findReaderByFirstName(String readerFirstName);

    List<Reader> findReaderByLastName(String readerLastName);

    List<Reader> findReaderByPersonalCode(String readerPersonalCode);

    List<Reader> findByFirstNameAndLastName(String readerFirstName, String readerLastName);

    List<Reader> getReaders();

    boolean containsReader (Reader reader);

}
