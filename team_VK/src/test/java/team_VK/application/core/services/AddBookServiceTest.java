package team_VK.application.core.services;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.domain.Book;
import team_VK.application.database.database_Admin.DatabaseInMemory;

public class AddBookServiceTest {


    @Test
    public void ShouldAddBook() {

        DatabaseInMemory databaseActual = new DatabaseInMemory();
        databaseActual.getListBooks().add(new Book("Foo", "Bar"));
        databaseActual.getListBooks().get(0).setID(1L);
        databaseActual.getListBooks().add(new Book("Buz", "Qux"));
        databaseActual.getListBooks().get(1).setID(2L);

        DatabaseInMemory databaseExpected = new DatabaseInMemory();

        databaseExpected.addBook(new Book("Foo", "Bar"));
        databaseExpected.addBook(new Book("Buz", "Qux"));


        Assert.assertEquals(databaseActual.getListBooks(), databaseExpected.getListBooks());

    }


}