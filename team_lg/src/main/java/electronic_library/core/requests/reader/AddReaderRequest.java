package electronic_library.core.requests.reader;

public class AddReaderRequest {

    private String readerFirstName;
    private String readerLastName;
    private String readerPersonalCode;
    private String readerPhoneNumber;
    private String readerEmail;
    private String readerAddress;

    public AddReaderRequest(String readerFirstName, String readerLastName, String readerPersonalCode, String readerPhoneNumber, String readerEmail) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.readerPhoneNumber = readerPhoneNumber;
        this.readerEmail = readerEmail;
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

    public String getReaderPhoneNumber() {
        return readerPhoneNumber;
    }

    public String getReaderEmail() {
        return readerEmail;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }
}
