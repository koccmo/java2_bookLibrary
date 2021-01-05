package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.ClientFirstNameFieldValidator;
import team_VK.application.core.services.standart_validators.ClientLastNameFieldValidator;
import team_VK.application.core.services.standart_validators.ClientPersonalCodeFieldValidator;
import team_VK.application.core.services.validators.AddClientServiceValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceValidatorTest {

    @Mock
    private ClientFirstNameFieldValidator clientFirstNameFieldValidator;
    @Mock
    private ClientLastNameFieldValidator clientLastNameFieldValidator;
    @Mock
    private ClientPersonalCodeFieldValidator clientPersonalCodeFieldValidator;
    @InjectMocks
    AddClientServiceValidator subject;


    @Test
    public void ShouldMergeThreeErrorsLists() {

        List<CoreError> errorsFirstName = Arrays.asList(new CoreError("firstName", "Field clientFirstName must be not empty"),
                new CoreError("firstName", "Field clientFirstName can't Space"));

        List<CoreError> errorsLastName = Arrays.asList(new CoreError("clientLastName", "Field clientLastName must be not empty"),
                new CoreError("clientLastName", "Field clientLastName can't be Space"));

        List<CoreError> errorsPersonalCode = Arrays.asList(new CoreError("clientPersonalCode", "Field clientPersonalCode haves illegal format. Should be 999999-99999"),
                new CoreError("clientPersonalCode", "Field clientPersonalCode is too short."));

        List<CoreError> errorsScope = Stream.concat(
                errorsFirstName.stream(),
                errorsLastName.stream())
                .collect(Collectors.toList());

        errorsScope = Stream.concat(
                errorsScope.stream(),
                errorsPersonalCode.stream())
                        .collect(Collectors.toList());

        AddClientRequest request = new AddClientRequest("Fuu", "Bar", "654321-45212");

        Mockito.when(clientFirstNameFieldValidator.validate(request.getClientFirstName() )).thenReturn(errorsFirstName);
        Mockito.when(clientLastNameFieldValidator.validate(request.getClientLastName())).thenReturn(errorsLastName);
        Mockito.when(clientPersonalCodeFieldValidator.validate(request.getClientPersonalCode() )).thenReturn(errorsPersonalCode);

        List<CoreError> actualErrors = subject.validate(request);

        Assert.assertEquals(actualErrors, errorsScope);
        Assert.assertEquals(actualErrors.size(), 6);
    }

}