package bookLibrary.core.request;

import java.util.Objects;

public class SearchBooksRequest {
    private String author;
    private String title;
    private Ordering ordering;
    private Paging paging;

    public SearchBooksRequest(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public SearchBooksRequest(String author, String title, Ordering ordering) {
        this.author = author;
        this.title = title;
        this.ordering = ordering;
    }

    public SearchBooksRequest(String author, String title, Ordering ordering, Paging paging) {
        this.author = author;
        this.title = title;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchBooksRequest request = (SearchBooksRequest) o;
        return Objects.equals(author, request.author)
                && Objects.equals(title, request.title)
                && Objects.equals(ordering, request.ordering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, ordering);
    }
}
