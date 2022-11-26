package bookLibrary.core.request;

public class AddBookRequest {
    private String author;
    private String title;

    public AddBookRequest(String author, String title) {
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
