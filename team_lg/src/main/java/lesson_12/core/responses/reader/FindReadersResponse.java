package lesson_12.core.responses.reader;

import lesson_12.core.domain.Reader;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

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
