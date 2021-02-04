package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class FindReaderByLastNameResponse extends CoreResponse {

    private boolean readerFind;

    public FindReaderByLastNameResponse(boolean readerFind) {
        this.readerFind = readerFind;
    }

    public FindReaderByLastNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderFind() {
        return readerFind;
    }
}
