package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderByLastNameResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderByLastNameResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderByLastNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
