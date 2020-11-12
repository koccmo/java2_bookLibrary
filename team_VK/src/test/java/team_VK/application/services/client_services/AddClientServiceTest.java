package team_VK.application.services.client_services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import team_VK.application.Book;
import team_VK.application.Client;
import team_VK.application.database.DatabaseInMemory;
import team_VK.application.database.database_Clients.DatabaseClientsInMemory;

import java.util.ArrayList;
import java.util.List;

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