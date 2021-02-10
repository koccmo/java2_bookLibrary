package lesson_12.core.requests.reader;

public class DeleteReaderByFirstNameRequest {

    private String readerFirstName;

    public DeleteReaderByFirstNameRequest(String readerFirstName) {
        this.readerFirstName = readerFirstName;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

}
