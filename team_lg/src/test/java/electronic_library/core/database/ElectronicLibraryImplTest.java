package electronic_library.core.database;

import electronic_library.core.database.book.ElectronicLibraryRepositoryImpl;
import electronic_library.core.domain.Book;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ElectronicLibraryImplTest {

    ElectronicLibraryRepositoryImpl electronicLibrary;

    @Before
    public void setUp() {
        electronicLibrary = new ElectronicLibraryRepositoryImpl();
        electronicLibrary.saveBook(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        electronicLibrary.saveBook(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        electronicLibrary.saveBook(new Book("ccc", "ccc", new BigDecimal("30.00"), 2003));
        electronicLibrary.saveBook(new Book("ddd", "ddd", new BigDecimal("40.00"), 2004));
    }
    @Test
    public void shouldTestSaveBook() {
        assertEquals(4, electronicLibrary.getElectronicLibrary().size());
        electronicLibrary.saveBook(new Book("eee", "eee", new BigDecimal("50.00"), 2005));
        assertEquals(5, electronicLibrary.getElectronicLibrary().size());
    }

    @Test
    public void shouldDeleteBookById() {
        assertEquals(4, electronicLibrary.getElectronicLibrary().size());
        electronicLibrary.deleteBookById(1L);
        assertEquals(3, electronicLibrary.getElectronicLibrary().size());
    }

    @Test
    public void shouldDeleteBookByTitle() {
        assertEquals(4, electronicLibrary.getElectronicLibrary().size());
        electronicLibrary.deleteBookByTitle("aaa");
        assertEquals(3, electronicLibrary.getElectronicLibrary().size());
    }

    @Test
    public void shouldDeleteBookByAuthor() {
        assertEquals(4, electronicLibrary.getElectronicLibrary().size());
        electronicLibrary.deleteBookByAuthor("aaa");
        assertEquals(3, electronicLibrary.getElectronicLibrary().size());
    }

    @Test
    public void shouldFindBookByTitle() {
        electronicLibrary.saveBook(new Book("bbb","bbb",new BigDecimal("20.00"),2010));
        electronicLibrary.saveBook(new Book("bbb","bbb",new BigDecimal("30.00"),2011));
        List<Book> result = electronicLibrary.findBookByTitle("bbb");
        assertEquals(3,result.size());
    }
    @Test
    public void shouldFindBookByAuthor() {
        electronicLibrary.saveBook(new Book("bbb","bbb",new BigDecimal("20.00"),2010));
        electronicLibrary.saveBook(new Book("bbb","bbb",new BigDecimal("30.00"),2011));
        List<Book> result = electronicLibrary.findBookByAuthor("bbb");
        assertEquals(3,result.size());
    }

}