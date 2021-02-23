package book_library.core.responses.Reader;

import book_library.core.domain.Reader;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class SearchReadersResponse extends CoreResponse {

    private List<Reader> readers;

    public SearchReadersResponse(List<Reader> readers, List<CoreError> errors) {
        super(errors);
        this.readers = readers;
    }

    public List<Reader> getReaders() {
        return readers;
    }
}
