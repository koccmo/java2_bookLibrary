package team_VK.application.core.services.client_services;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.domain.Client;
import team_VK.application.database.database_Clients.DatabaseClientsInMemory;

public class AddClientServiceTest  {

    @Test
    public void ShouldAddClient() {

        DatabaseClientsInMemory databaseClientActual = new DatabaseClientsInMemory();
        databaseClientActual.getListClients().add(new Client("Foo"));
        databaseClientActual.getListClients().get(0).setClientID(1L);
        databaseClientActual.getListClients().add(new Client("Bar"));
        databaseClientActual.getListClients().get(1).setClientID(2L);

        DatabaseClientsInMemory databaseClientsExpected = new DatabaseClientsInMemory();

        databaseClientsExpected.addClient (new Client("Foo"));
        databaseClientsExpected.addClient (new Client("Bar"));


        Assert.assertEquals(databaseClientActual.getListClients(), databaseClientsExpected.getListClients());

    }




}