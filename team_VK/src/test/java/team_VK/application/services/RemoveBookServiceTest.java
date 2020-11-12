package team_VK.application.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import team_VK.application.Book;
import team_VK.application.database.DatabaseInMemory;

import java.util.ArrayList;
import java.util.List;

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
        subject.removeBook(2L);

        Assert.assertEquals(databaseActual.getListBooks(), databaseExpected.getListBooks());

    }
}