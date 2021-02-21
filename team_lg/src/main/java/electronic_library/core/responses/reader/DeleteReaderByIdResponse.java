package electronic_library.core.responses.reader;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderByIdResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderByIdResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
