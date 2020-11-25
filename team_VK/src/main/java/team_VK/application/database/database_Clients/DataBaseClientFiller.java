package team_VK.application.database.database_Clients;

import team_VK.application.core.domain.Client;
import team_VK.application.database.database_Admin.Database;

public class DataBaseClientFiller {

    private DatabaseClients databaseClient;

    public DataBaseClientFiller(DatabaseClients databaseClient) {
        this.databaseClient = databaseClient;
    }

    public void fill() {


        Client client1 = new Client("Andrew Petroff");
        Client client2 = new Client("Piter McAndrew");
        Client client3 = new Client("Serjio Stecletto");
        Client client4 = new Client("Mash Uralmasheva");
        Client client5 = new Client("Pier Bezuhov");
        Client client6 = new Client("Ivan Smirnov");


    }
}
