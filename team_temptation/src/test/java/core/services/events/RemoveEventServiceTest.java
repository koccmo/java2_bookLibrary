package core.services.events;

import adventure_time.core.services.events.RemoveEventRequestValidator;
import adventure_time.core.services.events.RemoveEventService;
import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.database.events.EventDatabase;
import adventure_time.core.domain.Events;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RemoveEventServiceTest {

    @Mock
    private EventDatabase database;
    @Mock private RemoveEventRequestValidator validator;
    @InjectMocks
    private RemoveEventService service;

//    @BeforeEach
//    void setUp() {
//        validator = Mockito.mock(RemoveEventRequestValidator.class);
//        database = Mockito.mock(EventDatabase.class);
//        service = new RemoveEventService(database,validator);
//    }

    @Test
    void shouldReturnErrorWhenValidatorFailed () {

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("deletionWay", "Must be defined"));

        Mockito.when(validator.validate(any())).thenReturn(errors);

        RemoveEventResponse response = service.removeEvent(any());
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "deletionWay");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Must be defined");

        Mockito.verifyNoInteractions(database);
    }

    @Test
    void shouldRemoveEventByName () {

        RemoveEventRequest request = new RemoveEventRequest("Bike trip to Jurmala-12","byName");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

//        Mockito.when(database.removeById(any())).thenReturn(false);
        Mockito.when(database.removeByName(any())).thenReturn(true);

        RemoveEventResponse response = service.removeEvent(request);
        assertFalse(response.hasError());
        assertTrue(response.isSuccessRemoval());
    }

    @Test
    void shouldRemoveEventById () {

        RemoveEventRequest request = new RemoveEventRequest(Long.valueOf(1), "byId");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        Mockito.when(database.removeById(request.getEventId())).thenReturn(true);
//        Mockito.when(database.removeByName(request.getEventName())).thenReturn(false);

        RemoveEventResponse response = service.removeEvent(request);
        assertFalse(response.hasError());
        assertTrue(response.isSuccessRemoval());
    }

    @Test
    void shouldNotRemoveEvent () {

        RemoveEventRequest request = new RemoveEventRequest("Bike trip to Jurmala-12","byName");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

//        Mockito.when(database.removeById(any())).thenReturn(false);
        Mockito.when(database.removeByName(any())).thenReturn(false);

        RemoveEventResponse response = service.removeEvent(request);
        assertFalse(response.hasError());
        assertFalse(response.isSuccessRemoval());
    }
}