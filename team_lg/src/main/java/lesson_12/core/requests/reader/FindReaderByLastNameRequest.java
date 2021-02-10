package lesson_12.core.requests.reader;

public class FindReaderByLastNameRequest {

    private String readerLastName;

    public FindReaderByLastNameRequest(String readerLastName) {
        this.readerLastName = readerLastName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

}
