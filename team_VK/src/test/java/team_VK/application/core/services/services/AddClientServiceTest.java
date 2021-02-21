package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.AddClientResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.main_menu_services.AddClientService;
import team_VK.application.core.services.validators.AddClientServiceValidator;
import team_VK.application.database.ClientsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest  {

    @Mock private AddClientServiceValidator subject;
    @Mock private ClientsRepository databaseClient;
    @InjectMocks AddClientService service;


    @Test
    public void ShouldAddClientWhenResponseWithoutErrors() {
        List<CoreError> errors = new ArrayList<>();
        AddClientRequest request = new AddClientRequest("Foofoo", "Barbar", "654321-32165");
        Mockito.when(subject.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient (request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldReturnOneErrorsWhenClientFirstNameIsWrong() {

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("clientFirstName", "Field clientFirstName contains illegal characters"));
        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        Mockito.when(subject.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient(request);

        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "clientFirstName" );
        Assert.assertEquals(response.getErrorList().get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnTwoErrorsWhenClientFirstAndLastNameAreWrong() {

        List<CoreError> errors = Arrays.asList(new CoreError("clientFirstName", "Field clientFirstName contains illegal characters"),
                new CoreError("clientLastName", "Field clientLastName contains illegal characters"));
        AddClientRequest request = new AddClientRequest("FirstName", "LastName", "123456-78910");
        Mockito.when(subject.validate(request)).thenReturn(errors);

        AddClientResponse response = service.addClient(request);

        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.size(), 2);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "clientFirstName" );
        Assert.assertEquals(response.getErrorList().get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");

        Assert.assertEquals(response.getErrorList().get(1).getField(), "clientLastName" );
        Assert.assertEquals(response.getErrorList().get(1).getErrorMessage(), "Field clientLastName contains illegal characters");

    }
}