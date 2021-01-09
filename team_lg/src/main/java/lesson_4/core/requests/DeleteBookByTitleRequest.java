package lesson_4.core.requests;

public class DeleteBookByTitleRequest {

    private String bookTitle;

    public DeleteBookByTitleRequest(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

}
