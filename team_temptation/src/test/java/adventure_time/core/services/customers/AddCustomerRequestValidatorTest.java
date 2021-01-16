package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.AddCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddCustomerRequestValidatorTest {


    private AddCustomerRequestValidator validator = new AddCustomerRequestValidator();

    @Test
    void shouldNotReturnErrors() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void shouldReturnErrorCustomerNameIsNull() {
        AddCustomerRequest request = new AddCustomerRequest(null, "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerNameIsEmpty() {
        AddCustomerRequest request = new AddCustomerRequest("", "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerNameIsBlank() {
        AddCustomerRequest request = new AddCustomerRequest("       ", "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerNameIsTooShort() {
        AddCustomerRequest request = new AddCustomerRequest("Cus", "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain from 5 to 55 symbols");
    }

    @Test
    void shouldReturnErrorCustomerNameIsTooLong() {
        AddCustomerRequest request = new AddCustomerRequest("Customer X Customer X Customer X Customer X Customer X Customer X", "aaa@bbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain from 5 to 55 symbols");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsNull() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", null, "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsIncorrectWithDot() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbbcom", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Email is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsIncorrectWithEt() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaabbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Email is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsIncorrectNoLogin() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaabbb.com", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Email is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsIncorrectNoDomain() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Email is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerEmailIsIncorrectNoDomainNoLogin() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaabbbcom", "+37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerEmail");
        assertEquals(errors.get(0).getErrorMessage(), "Email is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerPhoneIsNull() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerPhoneIsEmpty() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerPhoneIsBlank() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Must be not empty");
    }

    @Test
    void shouldReturnErrorCustomerPhoneDoesNotStartWithCorrectCode() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "+37200000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Phone number is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerPhoneDoesNotStartWithPlus() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "#37100000000");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Phone number is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerPhoneTooShort() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "+3712828282");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Phone number is incorrect");
    }

    @Test
    void shouldReturnErrorCustomerPhoneTooLong() {
        AddCustomerRequest request = new AddCustomerRequest("Customer Name", "aaa@bbb.com", "+371282828282");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "customerPhone");
        assertEquals(errors.get(0).getErrorMessage(), "Phone number is incorrect");
    }

    @Test
    void shouldReturnSeveralErrors() {
        AddCustomerRequest request = new AddCustomerRequest("Cat", "@bbb.com", "+371282828282");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getErrorField(), "customerName");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain from 5 to 55 symbols");
        assertEquals(errors.get(1).getErrorField(), "customerEmail");
        assertEquals(errors.get(1).getErrorMessage(), "Email is incorrect");
        assertEquals(errors.get(2).getErrorField(), "customerPhone");
        assertEquals(errors.get(2).getErrorMessage(), "Phone number is incorrect");
    }
}