package lesson_12.core.requests.reader;

public class DeleteReaderByLastNameRequest {

    private String readerLastName;

    public DeleteReaderByLastNameRequest(String readerLastName) {
        this.readerLastName = readerLastName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

}
