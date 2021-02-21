package electronic_library.core.requests.book;

public class FindBookByTitleRequest {

    private String bookTitle;

    public FindBookByTitleRequest(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

}
