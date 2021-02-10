package electronic_library.core.database.reader;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("noJdbc")
public class ReaderRepositoryImpl implements ReaderRepository{

    private final List<Reader> readerList = new ArrayList<>();
    private Long readerId = 1L;

    @Override
    public void saveReader(Reader reader) {
        reader.setId(readerId);
        readerId++;
        readerList.add(reader);
    }

    @Override
    public boolean deleteReader(Reader reader) {
        for (Reader readers : readerList) {
            if ((readers.getReaderFirstName().equals(reader.getReaderFirstName())) &&
                    (readers.getReaderLastName().equals(reader.getReaderLastName())) &&
                    (readers.getReaderPersonalCode().equals(reader.getReaderPersonalCode()))) {
                readerList.remove(reader);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReaderById(Long id) {
        for (Reader reader:readerList) {
            if (reader.getId().equals(id)){
                readerList.remove(reader);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReaderByFirstName(String readerFirstName) {
        return readerList.removeIf(readers -> (readers.getReaderFirstName().equals(readerFirstName)));
    }

    @Override
    public boolean deleteReaderByLastName(String readerLastName) {
        return readerList.removeIf(readers -> (readers.getReaderLastName().equals(readerLastName)));
    }

    @Override
    public Optional<Reader> findReaderById(Long id) {
        return readerList.stream()
                .filter(readers -> readers.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Reader> findReaderByFirstName(String readerFirstName) {
        return readerList.stream()
                .filter(readers -> readers.getReaderFirstName().equalsIgnoreCase(readerFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reader> findReaderByLastName(String readerLastName) {
        return readerList.stream()
                .filter(readers -> readers.getReaderLastName().equalsIgnoreCase(readerLastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reader> findReaderByPersonalCode(String readerPersonalCode) {
        return readerList.stream()
                .filter(readers -> readers.getReaderLastName().equalsIgnoreCase(readerPersonalCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reader> findByFirstNameAndLastName(String readerFirstName, String readerLastName) {
        return readerList.stream()
                .filter(readers -> readers.getReaderFirstName().equals(readerFirstName))
                .filter(readers -> readers.getReaderLastName().equals(readerLastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reader> getReaders() {
        return readerList;
    }

    @Override
    public boolean containsReader(Reader reader) {
            for (Reader readers : readerList) {
                if ((readers.getReaderFirstName().equals(reader.getReaderFirstName())) &&
                        (readers.getReaderLastName().equals(reader.getReaderLastName())) &&
                        (readers.getReaderPersonalCode().equals(reader.getReaderPersonalCode()))) {
                    return true;
                }
            }
            return false;
    }
}
