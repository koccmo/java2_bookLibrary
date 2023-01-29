package bookLibrary.core.request;

public class GetBookIdRequest {
    private String author;
    private String title;

    public GetBookIdRequest(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
