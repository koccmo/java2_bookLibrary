package electronic_library.core.responses.book;

import electronic_library.core.domain.Book;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindBookByIdResponse extends CoreResponse {

    private Optional<Book> findBookById;

    public FindBookByIdResponse(Optional<Book> findBookById) {
        this.findBookById = findBookById;
    }

    public FindBookByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Book> getFindBookById() {
        return findBookById;
    }

}
