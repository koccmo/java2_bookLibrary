package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.main_menu_services.RemoveBookService;
import team_VK.application.core.services.validators.RemoveBookServiceValidator;
import team_VK.application.database.BookRepositoryInMemory;


import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class RemoveBookServiceTest {

    @Mock private RemoveBookServiceValidator subject;
    @Mock private BookRepositoryInMemory database ;
    @InjectMocks    private RemoveBookService service;



    @Test
    public void ShouldReturnNoErrorsWhenErrorsListIsEmpty() {

        List<CoreError> errors = new ArrayList<>();
        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea");
        Mockito.when(subject.validate(request, database)).thenReturn(errors);


        RemoveBookResponse response = service.removeBook(request);
        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldReturnErrorWhenErrorsListNotEmpty() {

        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea1");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Book ID", "ID not consist to Book Title"));
        Mockito.when(subject.validate(request, database)).thenReturn(errors);

        RemoveBookResponse response = service.removeBook(request);

        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Book ID");
    }


}
