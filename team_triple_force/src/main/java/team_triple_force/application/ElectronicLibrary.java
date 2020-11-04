package team_triple_force.application;

public interface ElectronicLibrary {

    Long saveBook(Book book);

    boolean deleteBook(Book book);

    boolean deleteBookById(Book BookId);

    void deleteBookByTitle(String bookTitle);

}
