package internet_store.core.service.client;

import internet_store.database.interfaces.ClientDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PrintClientService {
    @Autowired
    ClientDatabase clientDatabase;

    public void print() {

        if (clientDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Clients list:");
        clientDatabase.showReport();
    }
}