package electronic_library.core.responses.reader;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class FindReadersResponse extends CoreResponse {

    private List<Reader> readers;

    public FindReadersResponse(List<Reader> readers, List<CoreError> errors) {
        super(errors);
        this.readers = readers;
    }

    public List<Reader> getReaders() {
        return readers;
    }

}
