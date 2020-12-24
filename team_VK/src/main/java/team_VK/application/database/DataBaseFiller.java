package team_VK.application.database;

import team_VK.application.core.domain.Book;
import team_VK.application.core.services.DIDependency;

@DIComponent
public class DataBaseFiller {

 @DIDependency
 private Database database;

    public void fill() {

        database.addBook(new Book("The Old Man and Sea", "Hemingway E.", 10));
        database.addBook(new Book("Captain's doter", "Pushkin A.", 10));
        database.addBook(new Book("Broken pines", "Rainis J.", 5));
        database.addBook(new Book("Calm Don", "Shelohov M.", 15));
        database.addBook(new Book("Don's stories", "Shelohov M.", 5));

        database.addBook(new Book("Good bay, weapon", "Hemingway E.",5));
        database.addBook(new Book("Krokhotki", "Solzhenitsyn A.", 10));
        database.addBook(new Book("Read wheel", "Solzhenitsyn A.", 10));
        database.addBook(new Book("Crime and punishment", "Dostoevsky F.", 5));
        database.addBook(new Book("Idiot", "Dostoevsky F.", 15));

        database.addBook(new Book("War and World", "Tolstoy L.", 5));
        database.addBook(new Book("Three sisters", "Chekhov A.",5));
        database.addBook(new Book("State and anarchism", "Kropotkin A.", 10));
        database.addBook(new Book("In the first circle", "Solzhenitsyn A.", 10));
        database.addBook(new Book("Requiem for caravan PQ-17", "Pikul V.", 5));

        database.addBook(new Book("Moonsund", "Pikul V.", 15));
        database.addBook(new Book("Kornilov", "Denikin A.", 5));
        database.addBook(new Book("How to update the RABKRIN", "Lenin V.",5));
        database.addBook(new Book("Courtesans glitter and poverty", "Balzac A.", 5));
        database.addBook(new Book("93-rd", "Hugo V.",5));

        database.addBook(new Book("Drama on a hunt", "Chekhov A.", 15));
        database.addBook(new Book("Onegin", "Pushkin A.", 5));
        database.addBook(new Book("Poetry", "Tsvetajeva M.",5));
        database.addBook(new Book("Hamlet", "Shakespeare W.", 5));
        database.addBook(new Book("Poetry", "Blok A.",5));

    }

}
