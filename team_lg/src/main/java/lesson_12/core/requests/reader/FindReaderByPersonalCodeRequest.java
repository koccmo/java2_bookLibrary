package lesson_12.core.requests.reader;

public class FindReaderByPersonalCodeRequest {

    private String readerPersonalCode;

    public FindReaderByPersonalCodeRequest(String readerPersonalCode) {
        this.readerPersonalCode = readerPersonalCode;
    }

    public String getReaderPersonalCode() {
        return readerPersonalCode;
    }

}
