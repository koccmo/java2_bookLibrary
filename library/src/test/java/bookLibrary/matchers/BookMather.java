package bookLibrary.matchers;

import bookLibrary.core.domain.Book;
import org.mockito.ArgumentMatcher;

public class BookMather implements ArgumentMatcher<Book> {
    private String author;
    private String title;

    public BookMather(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public boolean matches(Book book) {
        return book.getAuthor().equals(author)
                && book.getTitle().equals(title);
    }
}
