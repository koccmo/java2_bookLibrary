package electronic_library.core.responses.reader;

import electronic_library.core.domain.Reader;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
