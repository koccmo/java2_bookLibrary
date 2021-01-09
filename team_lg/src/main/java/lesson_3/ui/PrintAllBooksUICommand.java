package lesson_3.ui;

import lesson_3.core.database.ElectronicLibrary;
import lesson_3.core.domain.Book;

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
