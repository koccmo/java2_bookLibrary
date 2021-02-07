package book_library.matchers;

import book_library.core.domain.Reader;
import org.mockito.ArgumentMatcher;

public class ReaderMatcher implements ArgumentMatcher<Reader> {

    private String firstName;
    private String lastName;

    public ReaderMatcher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean matches(Reader reader) {
        return reader.getFirstName().equals(firstName)
                && reader.getLastName().equals(lastName);
    }
}
