package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public abstract class Validator {

	void verifyNameAndSurname (String strToVerify, String fieldName, List<CodeError> errorList) {

		if (isEmpty(strToVerify)) {
			CodeError error = new CodeError(fieldName, "Null or empty string!");
			errorList.add(error);
		}
		else if (containSpaces(strToVerify)) {
			CodeError error = new CodeError(fieldName, fieldName + " shouldn't contain spaces or multiple words!");
			errorList.add(error);
		}
		else if (containDigits(strToVerify)) {
			CodeError error = new CodeError(fieldName, fieldName + " shouldn't contain digits!");
			errorList.add(error);
		}
		else if (containsSymbols(strToVerify)) {
			CodeError error = new CodeError(fieldName, fieldName + " shouldn't contain symbols!");
			errorList.add(error);
		}
	}

	void verifyCityAndCountry (String strToVerify, String fieldName, List<CodeError> errorList) {
		if (isEmpty(strToVerify)) {
			CodeError error = new CodeError(fieldName, "Null or empty string!");
			errorList.add(error);
		}
		else if (containDigits(strToVerify)) {
			CodeError error = new CodeError(fieldName, fieldName + " shouldn't contain digits!");
			errorList.add(error);
		}
		else if (containsSymbols(strToVerify)) {
			CodeError error = new CodeError(fieldName, fieldName + " shouldn't contain symbols!");
			errorList.add(error);
		}
	}

	boolean isEmpty (String str) {
		return str == null || str.isEmpty();
	}

	boolean containSpaces (String str) {
		if (str == null)
			return false;
		return str.length() - str.replaceAll("\\s", "").length() != 0;
	}

	boolean containDigits (String str) {
		if (str == null)
			return false;
		return str.length() - str.replaceAll("\\d", "").length() != 0;
	}

	boolean containsSymbols (String str) {
		if (str == null)
			return false;
		return str.length() - str.replaceAll("(?i)[^a-z-]+", "").length() != 0;
	}
}
