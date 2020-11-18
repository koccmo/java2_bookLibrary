package team_VK.application.core.services;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.database.database_Admin.DatabaseInMemory;
import team_VK.application.core.services.admin_services.RemoveBookService;

public class RemoveBookServiceTest {

    @Test
    public void ShouldRemoveBookByID() {


        DatabaseInMemory databaseActual = new DatabaseInMemory();
        databaseActual.addBook(new Book("Foo", "Bar"));
        databaseActual.addBook(new Book("Buz", "Qux"));
        databaseActual.addBook(new Book("Lorem", "Ipsum"));

        DatabaseInMemory databaseExpected = new DatabaseInMemory();
        databaseExpected.addBook(new Book("Foo", "Bar"));
        databaseExpected.addBook(new Book("Lorem", "Ipsum"));
        databaseExpected.getListBooks().get(1).setID(3);
        RemoveBookService subject = new RemoveBookService(databaseActual);
        subject.removeBook(new RemoveBookRequest(2L, "Buz"));

        Assert.assertEquals(databaseActual.getListBooks(), databaseExpected.getListBooks());

    }
}