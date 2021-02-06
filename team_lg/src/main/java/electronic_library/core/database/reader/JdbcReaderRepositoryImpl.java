package electronic_library.core.database.reader;

import electronic_library.core.database.book.BookRowMapper;
import electronic_library.core.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("jdbc")
public class JdbcReaderRepositoryImpl implements ReaderRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveReader(Reader reader) {
        jdbcTemplate.update("INSERT INTO readers (readerFirstName, readerLastName, readerPersonalCode, readerPhoneNumber, readerEmail) VALUES (?, ?, ?, ?)",
                reader.getReaderFirstName(), reader.getReaderLastName(), reader.getReaderPersonalCode(), reader.getReaderPhoneNumber(),reader.getReaderEmail()
        );
    }

    @Override
    public boolean deleteReader(Reader reader) {
        return jdbcTemplate.update("DELETE FROM readers WHERE readerFirstName = ? AND readerLastName = ? AND readerPersonalCode = ?",
                new Object[]{reader.getReaderFirstName(), reader.getReaderLastName(), reader.getReaderPersonalCode()}) > 0;
    }

    @Override
    public boolean deleteReaderById(Long id) {
        return jdbcTemplate.update("DELETE FROM readers WHERE id = ?", new Object[]{id}) == 1;
    }

    @Override
    public boolean deleteReaderByFirstName(String readerFirstName) {
        return jdbcTemplate.update("DELETE FROM readers WHERE readerFirstName = ?", new Object[]{readerFirstName}) > 0;
    }

    @Override
    public boolean deleteReaderByLastName(String readerLastName) {
        return jdbcTemplate.update("DELETE FROM readers WHERE readerLastName = ?", new Object[]{readerLastName}) > 0;
    }

    @Override
    public Optional<Reader> findReaderById(Long id) {
        try {
            Reader reader = (Reader) jdbcTemplate.queryForObject("SELECT * FROM readers WHERE id = ?", new Object[]{id}, new ReaderRowMapper());
            return Optional.ofNullable(reader);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Reader> findReaderByFirstName(String readerFirstName) {
        return jdbcTemplate.query("SELECT * FROM readers WHERE readerFirstName LIKE ?", new Object[]{readerFirstName}, new ReaderRowMapper());
    }

    @Override
    public List<Reader> findReaderByLastName(String readerLastName) {
        return jdbcTemplate.query("SELECT * FROM readers WHERE readerLastName LIKE ?", new Object[]{readerLastName}, new ReaderRowMapper());
    }

    @Override
    public List<Reader> findReaderByPersonalCode(String readerPersonalCode) {
        return jdbcTemplate.query("SELECT * FROM readers WHERE readerPersonalCode LIKE ?", new Object[]{readerPersonalCode}, new ReaderRowMapper());
    }

    @Override
    public List<Reader> findByFirstNameAndLastName(String readerFirstName, String readerLastName) {
        return jdbcTemplate.query("SELECT * FROM readers WHERE readerFirstName LIKE ? AND readerLastName LIKE ?", new Object[]{readerFirstName, readerLastName}, new ReaderRowMapper());
    }

    @Override
    public List<Reader> getReaders() {
        return jdbcTemplate.query("SELECT * FROM readers", new ReaderRowMapper());
    }
}
