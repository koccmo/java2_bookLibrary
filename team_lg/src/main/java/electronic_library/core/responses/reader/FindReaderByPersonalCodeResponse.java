package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class FindReaderByPersonalCodeResponse extends CoreResponse {

    private boolean readerFind;

    public FindReaderByPersonalCodeResponse(boolean readerFind) {
        this.readerFind = readerFind;
    }

    public FindReaderByPersonalCodeResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderFind() {
        return readerFind;
    }

}
