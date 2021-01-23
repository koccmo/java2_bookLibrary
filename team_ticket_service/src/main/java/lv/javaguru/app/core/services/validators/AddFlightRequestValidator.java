package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.domain.CodeError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddFlightRequestValidator {

	public List<CodeError> validate (AddFlightRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		errorList.addAll(validateTicketDepartureCity(request));
		errorList.addAll(validateTicketDestinationCity(request));
		errorList.addAll(validateTicketDepartureDate(request));

		return errorList;
	}

	private List<CodeError> validateTicketDepartureCity (AddFlightRequest request) {
		List<CodeError> depCityError = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(request.getFlight().getTicket().getFromCity(), "departure city");
		if (codeError.isPresent()) {
			depCityError.add(codeError.get());
			return depCityError;
		}

		strIsNotContainingDigits(request.getFlight().getTicket().getFromCity(), "departure city").ifPresent(depCityError::add);

		return depCityError;
	}

	private List<CodeError> validateTicketDestinationCity (AddFlightRequest request) {
		String destinationCity = request.getFlight().getTicket().getToCountry();

		List<CodeError> errors = new ArrayList<>();

		strIsNotNullOrEmpty(destinationCity, "destination city").ifPresent(errors::add);
		strIsNotContainingDigits(destinationCity, "destination city").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketDepartureDate (AddFlightRequest request) {
		LocalDate date = request.getFlight().getTicket().getDate();

		List<CodeError> errors = new ArrayList<>();

		isDateAfter(date, "departure date").ifPresent(errors::add);

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

	private Optional<CodeError> isDateAfter (LocalDate request, String field) {
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
		}
		catch (Exception ignore) {
			return false;
		}
	}

	private LocalDate parseStrToDate (String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(str, formatter);
	}

	private boolean isDateAfter (LocalDate date) {
		return date.isAfter(LocalDate.now());
	}
}
