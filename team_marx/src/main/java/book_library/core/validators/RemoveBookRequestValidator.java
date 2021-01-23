package book_library.core.validators;

import book_library.core.database.BookRowMapper;
import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveBookRequestValidator {

    @Autowired
    private Database database;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CoreError> validate(RemoveBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateIdPresentsInDatabase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateId(RemoveBookRequest request) {
        return (request.getBookIdToRemove() == null)
                ? Optional.of(new CoreError("id", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdPresentsInDatabase(RemoveBookRequest request) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Object[] args = new Object[] {request.getBookIdToRemove()};
        return (jdbcTemplate.query(sql, args, new BookRowMapper()).isEmpty())
                ? Optional.of(new CoreError("id", "No book with such id found!"))
                : Optional.empty();
    }
}
