package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;
import team_VK.application.core.services.validators.AddBookServiceValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceValidatorTest {


    @Mock
    private TitleFieldValidator titleFieldValidator;
    @Mock
    private AuthorFieldValidator authorFieldValidator;
    @InjectMocks
    AddBookServiceValidator subject;


    @Test
    public void ShouldMergeTwoErrorsLists() {

        List<CoreError> errorsTitle = Arrays.asList(new CoreError("bookTitle", "Field bookTitle must be not empty"),
                new CoreError("bookTitle", "Field bookTitle can't be Space"));
        List<CoreError> errorsAuthor = Arrays.asList(new CoreError("bookAuthor", "Field bookAuthor contains illegal characters"),
                new CoreError("bookAuthor", "Field bookAuthor can't be Space"));

        List<CoreError> errorsScope = Stream.concat(
                errorsTitle.stream(),
                errorsAuthor.stream())
                .collect(Collectors.toList());
        AddBookRequest request = new AddBookRequest("Title", "Author", 1);

        Mockito.when(titleFieldValidator.validate(request.getBookTitle())).thenReturn(errorsTitle);
        Mockito.when(authorFieldValidator.validate(request.getBookAuthor())).thenReturn(errorsAuthor);

        List<CoreError> actualErrors = subject.validate(request);

        Assert.assertEquals(actualErrors, errorsScope);
    }
}