package core.services.events;

import core.requests.events.RemoveEventRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveEventRequestValidatorTest {

    private RemoveEventRequestValidator validator = new RemoveEventRequestValidator();

    @Test
    void shouldNotReturnErrorsWhenDeletionValueIsEventIdAndDeletionWayIsById() {

        RemoveEventRequest request = new RemoveEventRequest (Long.valueOf(001234), "byId");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    void shouldNotReturnErrorsWhenDeletionValueIsEventNameAndDeletionWayIsByName() {

        RemoveEventRequest request = new RemoveEventRequest("eventName", "byName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }


    @Test
    void shouldReturnErrorsWhenEventIdIsZero() {

        RemoveEventRequest request = new RemoveEventRequest(Long.valueOf(0), "byId");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventId");
        assertEquals(errors.get(0).getErrorMessage(), "Must be above zero");
    }
    @Test
    void shouldReturnErrorsWhenEventIdIsNegative() {

        RemoveEventRequest request = new RemoveEventRequest(Long.valueOf(-100), "byId");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventId");
        assertEquals(errors.get(0).getErrorMessage(), "Must be above zero");
    }


    @Test
    void shouldReturnErrorsWhenDeletionWayIsEmpty() {

        RemoveEventRequest request = new RemoveEventRequest("eventName", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "deletionWay");
        assertEquals(errors.get(0).getErrorMessage(), "Must be defined");
    }
    @Test
    void shouldReturnErrorsWhenDeletionWayIsBlank() {

        RemoveEventRequest request = new RemoveEventRequest("eventName", " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "deletionWay");
        assertEquals(errors.get(0).getErrorMessage(), "Must be defined");
    }
    @Test
    void shouldReturnErrorsWhenDeletionWayIsNotByNameAndById() {

        RemoveEventRequest request = new RemoveEventRequest("eventName", "byRoute");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "deletionWay");
        assertEquals(errors.get(0).getErrorMessage(), "Must be \"by name\" or \"by ID-number\"");
    }


}