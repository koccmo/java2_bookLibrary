package book_library.core.database;

import book_library.core.domain.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ElectronicLibraryImplTest {

    ElectronicLibraryImpl electronicLibrary;

    @Before
    public void setUp() {
        electronicLibrary = new ElectronicLibraryImpl();
    }

    @Test
    public void shouldTestSaveBook() {
        electronicLibrary.saveBook(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        electronicLibrary.saveBook(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        electronicLibrary.saveBook(new Book("ccc", "ccc", new BigDecimal("30.00"), 2003));
        electronicLibrary.saveBook(new Book("ddd", "ddd", new BigDecimal("40.00"), 2004));
        Assert.assertEquals(4, electronicLibrary.getElectronicLibrary().size());
    }

}