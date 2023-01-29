package bookLibrary.core.response;

import bookLibrary.core.domain.Reader;

import java.util.List;

public class SearchReaderResponse extends CoreResponse{
    private List<Reader> readerList;

    public SearchReaderResponse(List<CoreError> errors, List<Reader> readerList) {
        super(errors);
        this.readerList = readerList;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }
}
