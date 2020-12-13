package core.services.events;

import core.requests.events.AddEventRequest;
import core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddEventRequestValidatorTest {

    private AddEventRequestValidator validator = new AddEventRequestValidator();

    @Test
    void shouldNotReturnErrors() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    void shouldReturnErrorWhenEventNameIsNull() {

        AddEventRequest request = new AddEventRequest (null, "bike trip", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenEventNameIsEmpty() {

        AddEventRequest request = new AddEventRequest ("", "bike trip", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenEventNameLessThanThreeSymbols() {

        AddEventRequest request = new AddEventRequest ("123", "bike trip", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventName");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain more than 3 symbols");
    }
    @Test //error
    void shouldReturnErrorWhenEventKindIsNull() {

        AddEventRequest request = new AddEventRequest ("eventName", null, 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventKind");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenEventKindIsEmpty() {

        AddEventRequest request = new AddEventRequest ("eventName", "", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventKind");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenEventKindDefinedWrongWay() {

        AddEventRequest request = new AddEventRequest ("eventName", "Wrong", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "eventKind");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect");
    }
    @Test
    void shouldReturnTwoErrorsWhenEventKindIsEmptyAndEventNameIsEmpty() {

        AddEventRequest request = new AddEventRequest ("", "", 5,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorField(), "eventName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
        assertEquals(errors.get(1).getErrorField(), "eventKind");
        assertEquals(errors.get(1).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenDurationHoursEqualZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 0,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "durationHours");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
    }
    @Test
    void shouldReturnErrorWhenDurationHoursLessZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", -1,
                6, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "durationHours");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
    }
    @Test
    void shouldReturnErrorsWhenMaxNumberParticipantsEqualZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                0, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorField(), "maxNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
        assertEquals(errors.get(1).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(1).getErrorMessage(), "Must not be bigger than maxNumber");
    }
    @Test
    void shouldReturnErrorsWhenMaxNumberParticipantsLessZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                -1, 4,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorField(), "maxNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
        assertEquals(errors.get(1).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(1).getErrorMessage(), "Must not be bigger than maxNumber");
    }
    @Test
    void shouldReturnErrorWhenMInNumberParticipantsEqualZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 0,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
    }
    @Test
    void shouldReturnErrorWhenMInNumberParticipantsLessZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, -1,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
    }
    @Test
    void shouldReturnErrorWhenMInNumberParticipantsBiggerThanMaxNumberParticipants() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 12,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be bigger than maxNumber");
    }
    @Test
    void shouldReturnErrorsWhenMaxNumberParticipantsLessZeroAndMinNumberParticipantsIsZero() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                -8, 0,"Route", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getErrorField(), "maxNumberParticipants");
        assertEquals(errors.get(0).getErrorMessage(), "Must be bigger than 0");
        assertEquals(errors.get(1).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(1).getErrorMessage(), "Must be bigger than 0");
        assertEquals(errors.get(2).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(2).getErrorMessage(), "Must not be bigger than maxNumber");
    }
    @Test
    void shouldReturnErrorWhenRouteIsNull() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 6,null, "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "route");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenRouteIsEmpty() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 6,"", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "route");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenRouteLessThanThreeSymbols() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                6, 4,"123", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "route");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain more than 3 symbols");
    }
    @Test
    void shouldReturnErrorWhenDetailDescriptionIsNull() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 6,"Route", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "detailDescription");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenDetailDescriptionIsEmpty() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                8, 6,"Route", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "detailDescription");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }
    @Test
    void shouldReturnErrorWhenDetailDescriptionLessThanThreeSymbols() {

        AddEventRequest request = new AddEventRequest ("eventName", "bike trip", 5,
                6, 4,"Route", "123");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "detailDescription");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain more than 3 symbols");
    }
    @Test
    void shouldReturnErrorsWhenAllOfFieldAreWrong() {

        AddEventRequest request = new AddEventRequest (null, "", 0,
                -8, 0,"123", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 8);
        assertEquals(errors.get(0).getErrorField(), "eventName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");

        assertEquals(errors.get(1).getErrorField(), "eventKind");
        assertEquals(errors.get(1).getErrorMessage(), "Must be not empty");

        assertEquals(errors.get(2).getErrorField(), "durationHours");
        assertEquals(errors.get(2).getErrorMessage(), "Must be bigger than 0");

        assertEquals(errors.get(3).getErrorField(), "maxNumberParticipants");
        assertEquals(errors.get(3).getErrorMessage(), "Must be bigger than 0");

        assertEquals(errors.get(4).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(4).getErrorMessage(), "Must be bigger than 0");

        assertEquals(errors.get(5).getErrorField(), "minNumberParticipants");
        assertEquals(errors.get(5).getErrorMessage(), "Must not be bigger than maxNumber");

        assertEquals(errors.get(6).getErrorField(), "route");
        assertEquals(errors.get(6).getErrorMessage(), "Must contain more than 3 symbols");

        assertEquals(errors.get(7).getErrorField(), "detailDescription");
        assertEquals(errors.get(7).getErrorMessage(), "Must be not empty");
    }
}