package lesson_12.core.requests.reader;

public class DeleteReaderByIdRequest {

    private Long readerIdToDelete;

    public DeleteReaderByIdRequest(Long readerIdToDelete) {
        this.readerIdToDelete = readerIdToDelete;
    }

    public Long getReaderIdToDelete() {
        return readerIdToDelete;
    }

}
