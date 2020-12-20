package team_VK.application.core.services.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddClientService;
import team_VK.application.core.services.AddClientServiceValidator;
import team_VK.application.core.services.matchers.ClientMatcher;
import team_VK.application.database.DatabaseClients;
import team_VK.application.database.DatabaseClientsInMemory;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest  {

    @Mock private AddClientServiceValidator validator;
    @Mock private DatabaseClients databaseClient;
    @InjectMocks AddClientService service;


    @Test
    public void ShouldReturnNoErrorsWhenErrorsListIsEmpty() {

        List<CoreError> errors = new ArrayList<>();
        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient(request);

        Assert.assertFalse(response.havesError());
        Assert.assertEquals(response.errorList.size(), 0);
    }

    @Test
    public void ShouldReturnErrorsWhenErrorsListNotEmpty() {

        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("clientLastName", "Field clientLastName contains illegal characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient(request);

        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "clientLastName" );
    }

    @Test
    public void ShouldAddClient() {

        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient(request);

        Assert.assertFalse(response.havesError());
        Assert.assertEquals(response.errorList.size(), 0);
        Mockito.verify(databaseClient).addClient(argThat(new ClientMatcher("FirstName", "LastName", "123456-78910")));

    }
}