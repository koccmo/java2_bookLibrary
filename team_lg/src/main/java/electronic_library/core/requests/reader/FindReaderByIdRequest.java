package electronic_library.core.requests.reader;

public class FindReaderByIdRequest {

    private String readerId;

    public FindReaderByIdRequest(String readerId) {
        this.readerId = readerId;
    }

    public String getReaderId() {
        return readerId;
    }

}
