package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderByFirstNameResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderByFirstNameResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderByFirstNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
