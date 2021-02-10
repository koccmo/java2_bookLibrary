package lesson_12.core.responses.reader;

import lesson_12.core.domain.Reader;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class AddReaderResponse extends CoreResponse {

    private Reader newReader;

    public AddReaderResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddReaderResponse(Reader newReader) {
        this.newReader = newReader;
    }

    public Reader getNewReader() {
        return newReader;
    }
}
