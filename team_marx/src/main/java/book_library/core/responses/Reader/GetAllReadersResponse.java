package book_library.core.responses.Reader;

import book_library.core.domain.Reader;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class GetAllReadersResponse extends CoreResponse {

    private List<Reader> readers;

    public GetAllReadersResponse(List<Reader> readers) {
        this.readers = readers;
    }

    public List<Reader> getReaders() {
        return readers;
    }
}
