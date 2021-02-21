package electronic_library.core.responses.reader;

import electronic_library.core.domain.Reader;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
