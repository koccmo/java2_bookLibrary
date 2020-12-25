package team_VK.application.database;

import team_VK.application.core.domain.Client;
import team_VK.application.core.services.DIDependency;

@DIComponent
public class DataBaseClientFiller {

    @DIDependency
    private DatabaseClients databaseClient;

//    public DataBaseClientFiller(DatabaseClients databaseClient) {
//        this.databaseClient = databaseClient;
//    }

    public void fill() {


        Client client1 = new Client("Andrew","Petroff", "123656-32145");
        Client client2 = new Client("Piter", "McAndrew", "211232-65446");
        Client client3 = new Client("Serjio", "Stecletto", "013212-65478");
        Client client4 = new Client("Mash", "Uralmasheva", "020196-45612");
        Client client5 = new Client("Pier", "Bezuhov", "151175-12365");
        Client client6 = new Client("Ivan", "Smirnov", "020202-65233");

        databaseClient.addClient(client1);
        databaseClient.addClient(client2);
        databaseClient.addClient(client3);
        databaseClient.addClient(client4);
        databaseClient.addClient(client5);
        databaseClient.addClient(client6);
    }
}
