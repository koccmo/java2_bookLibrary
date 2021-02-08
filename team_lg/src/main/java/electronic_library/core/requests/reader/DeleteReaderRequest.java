package electronic_library.core.requests.reader;

public class DeleteReaderRequest {

    private String readerFirstName;
    private String readerLastName;
    private String readerPersonalCode;

    public DeleteReaderRequest(String readerFirstName, String readerLastName, String readerPersonalCode) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

    public String getReaderPersonalCode() {
        return readerPersonalCode;
    }

}
