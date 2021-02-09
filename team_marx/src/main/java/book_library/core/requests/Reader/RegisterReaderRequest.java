package book_library.core.requests.Reader;

public class RegisterReaderRequest {

    private String readerFirstName;
    private String readerLastName;
    private Long personalCode;

    public RegisterReaderRequest(String readerFirstName, String readerLastName, Long personalCode) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.personalCode = personalCode;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

    public Long getPersonalCode() {
        return personalCode;
    }
}
