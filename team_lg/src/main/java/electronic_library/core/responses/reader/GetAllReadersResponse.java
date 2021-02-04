package electronic_library.core.responses.reader;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class GetAllReadersResponse extends CoreResponse {

    private List<Reader> readers;

    public GetAllReadersResponse(List<Reader> readers) {
        this.readers = readers;
    }

    public GetAllReadersResponse(List<CoreError> errors, List<Reader> readers) {
        super(errors);
    }

    public List<Reader> getReaders() {
        return readers;
    }
}
