package team_VK.application.database.database_Admin;

import team_VK.application.core.domain.Book;

public class DataBaseFiller {

    public  void  fill(){

        DatabaseInMemory database = new DatabaseInMemory();

        Book book1 = new Book("The Old Man and Sea", "E. Hemingway");
        Book book2 = new Book("Captain's doter","A. Pushkin");
        Book book3 = new Book("Broken pines", "J. Rainis");
        Book book4 = new Book("Calm Don", "M. Shelohov");
        Book book5 = new Book("Don's stories", "M. Shelohov");
        Book book6 = new Book("Good bay, weapon", "E. Hemingway");


    }

}
