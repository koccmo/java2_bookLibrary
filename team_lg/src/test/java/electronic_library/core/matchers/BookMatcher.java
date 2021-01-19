package electronic_library.core.matchers;

import electronic_library.core.domain.Book;
import org.mockito.ArgumentMatcher;

public class BookMatcher implements ArgumentMatcher<Book> {

    private String bookTitle;
    private String bookAuthor;

    public BookMatcher(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public boolean matches(Book book) {
        return book.getBookTitle().equals(bookTitle)
                && book.getBookAuthor().equals(bookAuthor);
    }
}
