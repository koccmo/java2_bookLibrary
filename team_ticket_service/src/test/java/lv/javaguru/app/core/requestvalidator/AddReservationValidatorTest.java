package lv.javaguru.app.core.requestvalidator;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.request.AddReservationRequestValidator;
import lv.javaguru.app.core.response.CodeError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddReservationValidatorTest {

    private AddReservationRequestValidator validator;

    @Test
    public void validPerson() {
        validator = new AddReservationRequestValidator();
        Person person = new Person("Sergejs", "Aleksejevs");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");
        AddReservationRequest request = new AddReservationRequest(person, ticket);

        List<CodeError> errors = validator.validate(request);
        assertEquals("Failed!", 0, errors.size());
    }

    @Test
    public void noNameTest() {
        validator = new AddReservationRequestValidator();
        Person person = new Person("", "Aleksejevs");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");
        AddReservationRequest request = new AddReservationRequest(person, ticket);

        List<CodeError> errors = validator.validate(request);
        assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
    }

    @Test
    public void noSurnameTest() {
        validator = new AddReservationRequestValidator();
        Person person = new Person("Sergejs", "");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");
        AddReservationRequest request = new AddReservationRequest(person, ticket);

        List<CodeError> errors = validator.validate(request);
        assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
    }
}