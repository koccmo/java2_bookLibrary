package core.services.events;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;
import core.responses.events.RemoveEventResponse;
import database.events.EventDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RemoveEventServiceTest {

    private EventDatabase database;
    private RemoveEventRequestValidator validator;
    private RemoveEventService service;

    @BeforeEach
    void setUp() {
        validator = Mockito.mock(RemoveEventRequestValidator.class);
        database = Mockito.mock(EventDatabase.class);
        service = new RemoveEventService(database,validator);
    }

    @Test
    void shouldReturnErrorWhenValidatorFailed () {

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("deletionWay", "Must be defined"));

        //RemoveEventRequest request = new RemoveEventRequest("","");

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

        Mockito.when(database.removeById(any())).thenReturn(false);
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
        Mockito.when(database.removeByName(request.getEventName())).thenReturn(false);

        RemoveEventResponse response = service.removeEvent(request);
        assertFalse(response.hasError());
        assertTrue(response.isSuccessRemoval());
    }

    @Test
    void shouldNotRemoveEvent () {

        RemoveEventRequest request = new RemoveEventRequest("Bike trip to Jurmala-12","byName");

        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());

        Mockito.when(database.removeById(any())).thenReturn(false);
        Mockito.when(database.removeByName(any())).thenReturn(false);

        RemoveEventResponse response = service.removeEvent(request);
        assertFalse(response.hasError());
        assertFalse(response.isSuccessRemoval());
    }
}