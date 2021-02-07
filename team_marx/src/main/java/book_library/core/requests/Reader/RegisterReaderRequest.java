package book_library.core.requests.Reader;

public class RegisterReaderRequest {

    private String readerFirstName;
    private String readerLastName;

    public RegisterReaderRequest(String readerFirstName, String readerLastName) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }
}
