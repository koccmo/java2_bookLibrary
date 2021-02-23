package book_library.core.responses.Reader;

import book_library.core.domain.Reader;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class RegisterReaderResponse extends CoreResponse {

    private Reader newReader;

    public RegisterReaderResponse(List<CoreError> errors) {
        super(errors);
    }

    public RegisterReaderResponse(Reader newReader) {
        this.newReader = newReader;
    }

    public Reader getNewReader() {
        return newReader;
    }
}
