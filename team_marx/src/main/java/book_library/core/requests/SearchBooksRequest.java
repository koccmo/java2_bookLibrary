package book_library.core.requests;

public class SearchBooksRequest {

    private String title;
    private String author;
    private Ordering ordering;

    public SearchBooksRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public SearchBooksRequest(String title, String author, Ordering ordering) {
        this.title = title;
        this.author = author;
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public boolean isTitleProvided() {return this.title != null && !this.title.isEmpty();}
    public boolean isAuthorProvided() {return this.author != null && !this.author.isEmpty();}
}
