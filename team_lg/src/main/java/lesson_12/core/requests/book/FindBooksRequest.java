package lesson_12.core.requests.book;

import lesson_12.core.requests.Ordering;
import lesson_12.core.requests.Paging;

public class FindBooksRequest {

    public String bookTitle;
    public String bookAuthor;
    private Ordering ordering;
    private Paging paging;

    public FindBooksRequest(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    public FindBooksRequest(String bookTitle, String bookAuthor, Ordering ordering, Paging paging) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.ordering = ordering;
        this.paging = paging;
    }

    public FindBooksRequest(String bookTitle, String bookAuthor, Ordering ordering) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.ordering = ordering;
    }

    public FindBooksRequest(String bookTitle, String bookAuthor, Paging paging) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.paging = paging;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public Ordering getOrdering() {
        return ordering;
    }
    public Paging getPaging() {
        return paging;
    }

    public boolean isBookTitleProvided() {
        return this.bookTitle != null && !this.bookTitle.isEmpty();
    }
    public boolean isBookAuthorProvided() {
        return this.bookAuthor != null && !this.bookAuthor.isEmpty();
    }

}
