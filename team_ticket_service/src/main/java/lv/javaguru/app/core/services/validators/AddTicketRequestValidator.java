package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.response.CodeError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddTicketRequestValidator {

	public List<CodeError> validate (AddTicketRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		errorList.addAll(validateTicketDepartureCity(request));
		errorList.addAll(validateTicketDestinationCity(request));
		errorList.addAll(validateTicketDepartureDate(request));
		errorList.addAll(validateTicketReturnDate(request));

		return errorList;
	}

	private List<CodeError> validateTicketDepartureCity (AddTicketRequest request) {
		List<CodeError> depCityError = new ArrayList<>();

		strIsNotNullOrEmpty(request.getTicket().getDeparture(), "departure city").ifPresent(depCityError::add);
		strIsNotContainingDigits(request.getTicket().getDeparture(), "departure city").ifPresent(depCityError::add);

		return depCityError;
	}

	private List<CodeError> validateTicketDestinationCity (AddTicketRequest request) {
		String destinationCity = request.getTicket().getDestination();

		List<CodeError> errors = new ArrayList<>();

		strIsNotNullOrEmpty(destinationCity, "destination city").ifPresent(errors::add);
		strIsNotContainingDigits(destinationCity, "destination city").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketDepartureDate (AddTicketRequest request) {
		String departDate = request.getTicket().getDepartDate();

		List<CodeError> errors = new ArrayList<>();
		strIsDateFormat(departDate, "departure date").ifPresent(errors::add);
		strIsDateAfter(departDate, "departure date").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketReturnDate (AddTicketRequest request) {
		String returnDate = request.getTicket().getReturnDate();

		List<CodeError> errors = new ArrayList<>();
		strIsDateFormat(returnDate, "Return date").ifPresent(errors::add);
		strIsDateAfter(returnDate, "Return date").ifPresent(errors::add);

		return errors;
	}

	private Optional<CodeError> strIsNotNullOrEmpty (String request, String field) {
		return (isNullOrEmpty(request))
				? Optional.of(new CodeError(field, "The string mustn't be empty!"))
				: Optional.empty();
	}

	private Optional<CodeError> strIsNotContainingDigits (String request, String field) {
		return (containsDigit(request))
				? Optional.of(new CodeError(field, "The string shouldn't contain digits!"))
				: Optional.empty();
	}

	private Optional<CodeError> strIsDateFormat (String request, String field) {
		return (!isRightDateFormat(request))
				? Optional.of(new CodeError(field, "Wrong date format!"))
				: Optional.empty();
	}

	private Optional<CodeError> strIsDateAfter (String request, String field) {
		return (!isDateAfter(request))
				? Optional.of(new CodeError(field, "Date shouldn't be in past!"))
				: Optional.empty();
	}

	private boolean containsDigit (String str) {
		return str.chars().anyMatch(Character::isDigit);
	}

	private boolean isNullOrEmpty (String str) {
		return (str == null || str.isEmpty());
	}

	private boolean isRightDateFormat (String str) {
		try {
			parseStrToDate(str);
			return true;
		} catch (Exception ignore) {
			return false;
		}
	}

	private LocalDate parseStrToDate (String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(str, formatter);
	}

	private boolean isDateAfter (String str) {
		if (isRightDateFormat(str))
			return parseStrToDate(str).isAfter(LocalDate.now());
		return true;
	}
}
