package lesson_12.core.requests.reader;

public class FindReaderByFirstNameRequest {

    private String readerFirstName;

    public FindReaderByFirstNameRequest(String readerFirstName) {
        this.readerFirstName = readerFirstName;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }
}
