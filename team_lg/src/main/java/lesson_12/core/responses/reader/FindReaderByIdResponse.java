package lesson_12.core.responses.reader;

import lesson_12.core.domain.Reader;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindReaderByIdResponse extends CoreResponse {

    private Optional<Reader> findReaderById;

    public FindReaderByIdResponse(Optional<Reader> findReaderById) {
        this.findReaderById = findReaderById;
    }

    public FindReaderByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Reader> getFindReaderById() {
        return findReaderById;
    }
}
