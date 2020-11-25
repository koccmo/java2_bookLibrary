package team_VK.application.database.database_Admin;

import team_VK.application.core.domain.Book;

public class DataBaseFiller {
    private Database database;

    public DataBaseFiller(Database database) {
        this.database = database;
    }

    public void fill() {


        Book book1 = new Book("The Old Man and Sea", "E. Hemingway", 10);
        Book book2 = new Book("Captain's doter", "A. Pushkin", 10);
        Book book3 = new Book("Broken pines", "J. Rainis", 5);
        Book book4 = new Book("Calm Don", "M. Shelohov", 15);
        Book book5 = new Book("Don's stories", "M. Shelohov", 5);
        Book book6 = new Book("Good bay, weapon", "E. Hemingway",5);

        database.addBook(book1);
        database.addBook(book2);
        database.addBook(book3);
        database.addBook(book4);
        database.addBook(book5);
        database.addBook(book6);

    }

}
