package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class FindReaderByFirstNameResponse extends CoreResponse {

    private boolean readerFind;

    public FindReaderByFirstNameResponse(boolean readerFind) {
        this.readerFind = readerFind;
    }

    public FindReaderByFirstNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderFind() {
        return readerFind;
    }

}
