package lesson_3.core.service.print_client_service;

import lesson_3.database.client_database.InnerClientDatabase;

public class PrintClientService {
    final InnerClientDatabase clientDatabase;

    public PrintClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public void print() {
        clientDatabase.showReport();
    }
}