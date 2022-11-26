package bookLibrary.core.response;

import java.util.List;

public class PrintAllBooksTitleResponse extends CoreResponse{
    List<String> bookList;

    public PrintAllBooksTitleResponse(List<String> bookList) {
        this.bookList = bookList;
    }

    public List<String> getBookList() {
        return bookList;
    }
}
