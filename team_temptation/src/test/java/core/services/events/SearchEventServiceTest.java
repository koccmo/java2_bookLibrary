package core.services.events;

import adventure_time.core.services.events.SearchEventRequestValidator;
import adventure_time.core.requests.Ordering;
import adventure_time.core.services.events.SearchEventService;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.SearchEventResponse;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SearchEventServiceTest {

//     private SearchEventRequestValidator validator;
//     private SearchEventService service;
//     private EventDatabase database;

    @Mock private SearchEventRequestValidator validator;
    @Mock private EventDatabase database;
    @InjectMocks private SearchEventService service;


//    @BeforeEach
//    void setUp() {
//        validator = Mockito.mock(SearchEventRequestValidator.class);
//        database = Mockito.mock(EventDatabase.class);
//        service = new SearchEventService(database, validator);
//    }

    @Test
    void shouldReturnResponseWithErrorWhenValidatorFailed() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(0, 0);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("orderDirect", "Must be specified as the sort criteria is specified"));

        Mockito.when(validator.validate(any())).thenReturn(errors);

        SearchEventResponse response = service.searchEvent(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorField(), "orderDirect");
        assertEquals(response.getErrors().get(0).getErrorMessage(), "Must be specified as the sort criteria is specified");

        Mockito.verifyNoInteractions(database);
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
    }

    @Test
    void shouldReturnResponseErrorResponseNoItems() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertTrue(response.hasError());

        assertEquals(response.getErrors().size(), 1);
        assertNull(response.getErrors().get(0).getErrorField());
        assertEquals(response.getErrors().get(0).getErrorMessage(), "There are no items in the database for this request");

    }

    @Test
    void shouldReturnResponseWithListOfAllEvents() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");

    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByDuration() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 15, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByEventDuration(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByRoute() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "Riga Jurmala", 0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByRoute(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByEventKindAndDuration() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("Bike trip to Jurmala-12", "", 15, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByEventKindAndDuration(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByEventKindAndRoute() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("Bike trip to Jurmala-12", "Riga Jurmala", 0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByEventKindAndRoute(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByEventDurationAndRoute() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "Riga Jurmala", 12, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByEventDurationAndRoute(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsFoundByEventKindAndDurationAndRoute() {
        Ordering ordering = new Ordering("", "");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("Bike trip to Jurmala-12", "Riga Jurmala", 12, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        List<Events> events = new ArrayList<>();
        Events event = new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner");
        events.add(event);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(database.findByEventKindAndDurationAndRoute(request)).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),1);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByNameInAscending() {

        Ordering ordering = new Ordering("eventNamed", "ascending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("Bus trip to Sigulga and Cesis", "bus trip",
                12, 25, 12, "Riga Sigulda Cesis",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("Motorcycle trip to the forest", "motorcycle trip",
                8, 4, 4, "Riga Saulkrasti",
                "Motorcycle trip. Dinner"));

        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(0).getEventName(), "Bike trip to Jurmala-12");
        assertEquals(response.getEvents().get(1).getEventName(), "Bus trip to Sigulga and Cesis");
        assertEquals(response.getEvents().get(2).getEventName(), "Motorcycle trip to the forest");
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByNameInDescending() {

        Ordering ordering = new Ordering("eventNamed", "descending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();

        events.add(new Events("Motorcycle trip to the forest", "motorcycle trip",
                8, 4, 4, "Riga Saulkrasti",
                "Motorcycle trip. Dinner"));
        events.add(new Events("Bus trip to Sigulga and Cesis", "bus trip",
                12, 25, 12, "Riga Sigulda Cesis",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("Bike trip to Jurmala-12", "bike trip",
                12, 10, 2, "Riga Jurmala",
                "Bike trip. Lunch. Dinner"));
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(2).getEventName(), "Bike trip to Jurmala-12");
        assertEquals(response.getEvents().get(1).getEventName(), "Bus trip to Sigulga and Cesis");
        assertEquals(response.getEvents().get(0).getEventName(), "Motorcycle trip to the forest");
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByRouteInDescending() {

        Ordering ordering = new Ordering("route", "descending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("One", "bike trip",
                12, 10, 2, "Z",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("Two", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("Three", "bus trip",
                12, 25, 12, "A",
                "Bus trip. Lunch. Dinner"));

        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(0).getEventName(), "One");
        assertEquals(response.getEvents().get(1).getEventName(), "Two");
        assertEquals(response.getEvents().get(2).getEventName(), "Three");
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByRouteInAscending() {

        Ordering ordering = new Ordering("route", "ascending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("One", "bike trip",
                12, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("Two", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("Three", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));

        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(0).getEventName(), "One");
        assertEquals(response.getEvents().get(1).getEventName(), "Two");
        assertEquals(response.getEvents().get(2).getEventName(), "Three");
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByDurationInAscending() {

        Ordering ordering = new Ordering("durationHours", "ascending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("One", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("Two", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("Three", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));

        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(0).getDurationHours(), 5);
        assertEquals(response.getEvents().get(1).getDurationHours(), 8);
        assertEquals(response.getEvents().get(2).getDurationHours(), 12);
    }

    @Test
    void shouldReturnResponseWithListOfEventsSortedByDurationInDescending() {

        Ordering ordering = new Ordering("durationHours", "descending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("Three", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("Two", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("One", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),3);
        assertEquals(response.getEvents().get(2).getDurationHours(), 5);
        assertEquals(response.getEvents().get(1).getDurationHours(), 8);
        assertEquals(response.getEvents().get(0).getDurationHours(), 12);
    }

    @Test
    void shouldReturnResponseWithListOfEventsFirstPage() {

        Ordering ordering = new Ordering("eventNamed", "ascending");
        Paging paging = new Paging(1, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("01", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("02", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("03", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("04", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("05", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("06", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("07", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("08", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("09", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("10", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("11", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("12", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),8);
        assertEquals(response.getEvents().get(0).getEventName(), "01");
        assertEquals(response.getEvents().get(3).getEventName(), "04");
        assertEquals(response.getEvents().get(7).getEventName(), "08");
    }

    @Test
    void shouldReturnResponseWithListOfEventsLastPage() {

        Ordering ordering = new Ordering("eventNamed", "ascending");
        Paging paging = new Paging(2, 8);
        SearchEventRequest request = new SearchEventRequest("", "", 0, ordering, paging);

        List<CoreError> errors = new ArrayList<>();
        Mockito.when(validator.validate(request)).thenReturn(errors);

        List<Events> events = new ArrayList<>();
        events.add(new Events("01", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("02", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("03", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("04", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("05", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("06", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("07", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("08", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("09", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        events.add(new Events("10", "bus trip",
                12, 25, 12, "Z",
                "Bus trip. Lunch. Dinner"));
        events.add(new Events("11", "motorcycle trip",
                8, 4, 4, "M",
                "Motorcycle trip. Dinner"));
        events.add(new Events("12", "bike trip",
                5, 10, 2, "A",
                "Bike trip. Lunch. Dinner"));
        Mockito.when(database.getEventsList()).thenReturn(events);

        SearchEventResponse response = service.searchEvent(request);
        assertFalse(response.hasError());

        assertEquals(response.getEvents().size(),4);
        assertEquals(response.getEvents().get(0).getEventName(), "09");
        assertEquals(response.getEvents().get(2).getEventName(), "11");
        assertEquals(response.getEvents().get(3).getEventName(), "12");
    }
}