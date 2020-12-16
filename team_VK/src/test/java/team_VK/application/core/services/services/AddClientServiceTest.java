package team_VK.application.core.services.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddClientService;
import team_VK.application.core.services.AddClientServiceValidator;
import team_VK.application.database.DatabaseClients;
import team_VK.application.database.DatabaseClientsInMemory;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddClientServiceTest  {

    private DatabaseClients databaseClient;
    private AddClientServiceValidator validator;
    private List<CoreError> errors;

    @Before
    public void setup() {
        DIApplicationContextBuilder diApplicationContextBuilder = new DIApplicationContextBuilder();
        databaseClient = new DatabaseClientsInMemory();
        validator = Mockito.mock(AddClientServiceValidator.class);
        errors = new ArrayList<>();
    }

    @Test
    public void ShouldReturnNoErrorsWhenErrorsListIsEmpty() {

        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddClientService service = new AddClientService();

        AddClientResponse response = service.addClient(request);
        Assert.assertFalse(response.havesError());
        Assert.assertEquals(response.errorList.size(), 0);
    }

    @Test
    public void ShouldReturnErrorsWhenErrorsListNotEmpty() {

        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        errors.add(new CoreError("clientLastName", "Field clientLastName contains illegal characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddClientService service = new AddClientService();

        AddClientResponse response = service.addClient(request);
        Assert.assertTrue(response.havesError());
            Assert.assertEquals(response.errorList.size(), 1);
            Assert.assertEquals(response.getErrorList().get(0).getField(), "clientLastName" );
    }

    @Test
    public void ShouldAddClient() {

        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddClientService service = new AddClientService();

        AddClientResponse response = service.addClient(request);

        Assert.assertEquals(databaseClient.getListClients().size(), 1);
        Assert.assertEquals(databaseClient.getListClients().get(0).getClientFirstName(), "FirstName");
        Assert.assertEquals(databaseClient.getListClients().get(0).getClientLastName(), "LastName");
        Assert.assertEquals(databaseClient.getListClients().get(0).getClientPersonalCode(), "123456-78910");
    }
}