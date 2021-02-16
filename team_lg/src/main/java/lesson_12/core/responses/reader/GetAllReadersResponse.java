package lesson_12.core.responses.reader;

import lesson_12.core.domain.Reader;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

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
