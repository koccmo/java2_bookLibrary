package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AddFlightRequestValidator {

	public List<CodeError> validate (AddFlightRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		errorList.addAll(validateTicketUserName(request));
		//errorList.addAll(validateTicketUserSurname(request));

		errorList.addAll(validateTicketOriginCountry(request));
		errorList.addAll(validateTicketOriginCity(request));
		errorList.addAll(validateTicketDestinationCountry(request));
		errorList.addAll(validateTicketDestinationCity(request));
	//	errorList.addAll(validateTicketDepartureDate(request));

		return errorList;
	}

	private List<CodeError> validateTicketUserName (AddFlightRequest request) {
		String userName = request.getUser().getUsername();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(userName, "User name");
		if (codeError.isPresent()) {
			errors.add(codeError.get());

			return errors;
		}

		strIsNotContainingDigits(userName, "User name").ifPresent(errors::add);
		strIsNotContainingSymbols(userName, "User name").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketUserSurname (AddFlightRequest request) {
		String userSurname = request.getUser().getPassword();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(userSurname, "User surname");
		if (codeError.isPresent()) {
			errors.add(codeError.get());

			return errors;
		}

		strIsNotContainingDigits(userSurname, "User surname").ifPresent(errors::add);
		strIsNotContainingSymbols(userSurname, "User surname").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketOriginCountry (AddFlightRequest request) {
		String originCountry = request.getTicket().getOriginCountry();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(originCountry, "Origin country");
		if (codeError.isPresent()) {
			errors.add(codeError.get());
			return errors;
		}

		strIsNotContainingDigits(originCountry, "Origin country").ifPresent(errors::add);
		strIsNotContainingSymbols(originCountry, "Origin country").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketOriginCity (AddFlightRequest request) {
		String originCity = request.getTicket().getOriginCity();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(originCity, "Origin city");
		if (codeError.isPresent()) {
			errors.add(codeError.get());
			return errors;
		}

		strIsNotContainingDigits(originCity, "Origin city").ifPresent(errors::add);
		strIsNotContainingSymbols(originCity, "Origin city").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketDestinationCountry (AddFlightRequest request) {
		String destinationCountry = request.getTicket().getDestinationCountry();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(destinationCountry, "Destination country");
		if (codeError.isPresent()) {
			errors.add(codeError.get());
			return errors;
		}

		strIsNotContainingDigits(destinationCountry, "Destination country").ifPresent(errors::add);
		strIsNotContainingSymbols(destinationCountry, "Destination country").ifPresent(errors::add);

		return errors;
	}

	private List<CodeError> validateTicketDestinationCity (AddFlightRequest request) {
		String destinationCity = request.getTicket().getDestinationCity();

		List<CodeError> errors = new ArrayList<>();

		Optional<CodeError> codeError = strIsNotNullOrEmpty(destinationCity, "Destination city");
		if (codeError.isPresent()) {
			errors.add(codeError.get());
			return errors;
		}

		strIsNotContainingDigits(destinationCity, "Destination city").ifPresent(errors::add);
		strIsNotContainingSymbols(destinationCity, "Destination city").ifPresent(errors::add);

		return errors;
	}

	//private List<CodeError> validateTicketDepartureDate (AddFlightRequest request) {
	//	Date date = request.getTicket().getDepartureDate();
//
	//	List<CodeError> errors = new ArrayList<>();
//
	//	Optional<CodeError> codeError = dateIsNotNullOrEmpty(date, "Departure date");
	//	if (codeError.isPresent()) {
	//		errors.add(codeError.get());
	//		return errors;
	//	}
//
	//	isDateAfter(date, "Departure date").ifPresent(errors::add);
//
	//	return errors;
	//}


	private Optional<CodeError> dateIsNotNullOrEmpty (Date dateRequest, String field) {
		return (isNullOrEmpty(dateRequest))
				? Optional.of(new CodeError(field, "The string mustn't be empty!"))
				: Optional.empty();
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

	private Optional<CodeError> strIsNotContainingSymbols (String request, String field) {
		return (containsSymbols(request))
				? Optional.of(new CodeError(field, "The string shouldn't contain symbols!"))
				: Optional.empty();
	}

	private Optional<CodeError> isDateAfter (Date request, String field) {
		return (!isDateAfter(request))
				? Optional.of(new CodeError(field, "Date shouldn't be in past!"))
				: Optional.empty();
	}

	private boolean containsDigit (String str) {
		if (str == null)
			return false;
		return str.chars().anyMatch(Character::isDigit);
	}

	private boolean containsSymbols (String str) {
		if (str == null)
			return false;
		return str.length() - str.replaceAll("(?i)[^a-z-\\s\\d]", "").length() != 0;
	}

	private boolean isNullOrEmpty (Date date) {
		return (date == null);
	}

	private boolean isNullOrEmpty (String str) {
		return (str == null || str.isEmpty());
	}

	private boolean isDateAfter (Date date) {
		return date.after(new Date());
	}
}
