package lesson_12.core.requests.book;

public class DeleteBookByTitleRequest {

    private String bookTitle;

    public DeleteBookByTitleRequest(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

}
