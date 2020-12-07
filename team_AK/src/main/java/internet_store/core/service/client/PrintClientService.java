package internet_store.core.service.client;

import internet_store.database.client_database.InnerClientDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class PrintClientService {
    @DIDependency
    InnerClientDatabase clientDatabase;

//    public PrintClientService() {}

//    public PrintClientService(InnerClientDatabase clientDatabase) {
//        this.clientDatabase = clientDatabase;
//    }

    public void print() {

        if (clientDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Clients list:");
        clientDatabase.showReport();
    }
}