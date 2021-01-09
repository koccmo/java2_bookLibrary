package core.services.events;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.services.events.AddEventRequestValidator;
import adventure_time.core.services.events.AddEventService;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.AddEventResponse;
import adventure_time.database.events.EventDatabase;
import adventure_time.core.domain.Events;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
class AddEventServiceTest {


    @Mock
    private AddEventRequestValidator validator;
    @Mock private EventDatabase database;
    @InjectMocks
    private AddEventService service;

//    @BeforeEach
//    void setUp() {
//        validator = Mockito.mock(AddEventRequestValidator.class);
//        database = Mockito.mock(EventDatabase.class);
//        service = new AddEventService(database, validator);
//    }

    @Test
    void shouldReturnErrorWhenValidatorFailed () {

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("eventName", "Must be not empty"));

        AddEventRequest request = new AddEventRequest("", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");

        Mockito.when(validator.validate(any())).thenReturn(errors);

        AddEventResponse response = service.addEvent(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "eventName");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Must be not empty");

        Mockito.verifyNoInteractions(database);
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    void shouldReturnErrorWhenEventAlreadyExistInDatabase () {

        AddEventRequest request = new AddEventRequest("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.add(any())).thenReturn(false);

        AddEventResponse response = service.addEvent(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "eventName");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "The event \"" + request.getEventName() + "\" already exists");

    }

    @Test
    void shouldAddEventToDatabase () {

        AddEventRequest request = new AddEventRequest("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.add(any())).thenReturn(true);

        AddEventResponse response = service.addEvent(request);
        assertFalse(response.hasError());

    }

}