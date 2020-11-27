package book_library.ui;

import book_library.core.domain.Book;
import book_library.core.database.ElectronicLibrary;

import java.util.List;

public class PrintAllBooksUICommand implements UICommand{
    private final ElectronicLibrary electronicLibrary;

    public PrintAllBooksUICommand(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    @Override
    public void execute() {
        List<Book> bookList = electronicLibrary.getElectronicLibrary();
        System.out.println("================================================================================================");
        System.out.println("ELECTRONIC LIBRARY LIST:");
        bookList.stream().map(book -> book.toString() + "\n").forEach(System.out::print);
        System.out.println("================================================================================================");
    }

}
