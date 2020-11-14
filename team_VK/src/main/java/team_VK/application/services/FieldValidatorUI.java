package team_VK.application.services;

import team_VK.application.requests.AddBookRequest;
import team_VK.application.responses.CoreError;

import java.util.List;
import java.util.stream.IntStream;

public class FieldValidatorUI {



    public List<CoreError> bookAuthorFieldValidate(AddBookRequest request, List<CoreError> errors) {
        if (request.bookAuthor == null || request.bookAuthor.equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor must be not empty");
            errors.add(error);
        }

        if (request.bookAuthor.trim().equals("") && !request.bookAuthor.equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor can't be Space");
            errors.add(error);
        }

        if (request.bookAuthor.trim().length() <= 3 && !request.bookAuthor.trim().equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor is too short");
            errors.add(error);
        }

        IntStream streamFromString = request.bookAuthor.chars();
        boolean b = streamFromString
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 193))||
                        (value >= 256));
        if (b) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
            errors.add(error);
        }
        return errors;
    }




    public List<CoreError> bookTitleFieldValidate(AddBookRequest request, List<CoreError> errors) {
        if (request.bookTitle == null || request.bookTitle.equals("")) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle must be not empty");
            errors.add(error);
        }

        if (request.bookTitle.trim().equals("") && !request.bookTitle.equals("")) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle can't be Space");
            errors.add(error);
        }

        if (request.bookTitle.trim().length() <= 3 && !request.bookTitle.trim().equals("") ) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle is too short");
            errors.add(error);
        }

        IntStream streamFromString = request.bookTitle.chars();
        boolean b = streamFromString
                .anyMatch(value -> (value <= 31) ||
                        ((value >=34) && (value<=43)) ||
                        ((value >=60) && (value<=64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 193))  ||
                        (value >= 256));
        if (b) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
            errors.add(error);
        }
        return  errors;
    }

}
