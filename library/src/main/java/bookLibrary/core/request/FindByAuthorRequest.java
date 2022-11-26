package bookLibrary.core.request;

public class FindByAuthorRequest {
    private String author;

    public FindByAuthorRequest(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
