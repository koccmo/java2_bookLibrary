package lesson_12.core.requests.reader;

import lesson_12.core.domain.Reader;

public class AddReaderRequest {

    private Reader reader;

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

    public AddReaderRequest(String readerFirstName, String readerLastName, String readerPersonalCode, String readerPhoneNumber, String readerEmail, String readerAddress) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.readerPhoneNumber = readerPhoneNumber;
        this.readerEmail = readerEmail;
        this.readerAddress = readerAddress;
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

    public Reader getReader(){
        return reader;
    }
}
