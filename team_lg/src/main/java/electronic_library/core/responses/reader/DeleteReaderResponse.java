package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
