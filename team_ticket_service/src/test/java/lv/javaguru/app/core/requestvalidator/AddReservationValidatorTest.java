package lv.javaguru.app.core.requestvalidator;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;

import org.junit.Ignore;
import org.junit.Test;

public class AddReservationValidatorTest {

    private AddTicketRequestValidator validator;

    @Test
    public void validPerson() {
        validator = new AddTicketRequestValidator();
        User user = new User("Sergejs", "Aleksejevs");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");
     //   Reservation reservation = new Reservation(person, ticket);
     //   AddTicketRequest request = new AddTicketRequest(reservation);

       /// List<CodeError> errors = validator.validate(request);
    //    assertEquals("Failed!", 0, errors.size());
    }

    @Test
    public void noNameTest() {
        validator = new AddTicketRequestValidator();
        User user = new User("", "Aleksejevs");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");

     //   Reservation reservation = new Reservation(person, ticket);

      //  AddTicketRequest request = new AddTicketRequest(reservation);

    //    List<CodeError> errors = validator.validate(request);
   //     assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
    }

    @Test
    @Ignore
    public void noSurnameTest() {
        validator = new AddTicketRequestValidator();
        User user = new User("Sergejs", "");
        Ticket ticket = new Ticket("Riga", "London", "12.11.2020", "22.11.2020", "11A");

   //  Reservation reservation = new Reservation(person, ticket);

   //  AddTicketRequest request = new AddTicketRequest(reservation);

   //  List<CodeError> errors = validator.validate(request);
   //  assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
    }
}