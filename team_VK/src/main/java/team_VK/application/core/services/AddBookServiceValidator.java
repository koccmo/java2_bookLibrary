package team_VK.application.core.services;

import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddBookServiceValidator {

    List<CoreError> errors = new ArrayList<>();

    public List <CoreError> validate (AddBookRequest request){

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

        IntStream streamFromBookAuthor = request.bookAuthor.chars();
        boolean bookAuthorContainsIllegalCharts = streamFromBookAuthor
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 193))||
                        (value >= 256));
        if (bookAuthorContainsIllegalCharts) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
            errors.add(error);
        }

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

        IntStream streamFromBookTitle = request.bookTitle.chars();
        boolean bookTitleContainsIllegalCharts = streamFromBookTitle
                .anyMatch(value -> (value <= 31) ||
                        ((value >=34) && (value<=43)) ||
                        ((value >=60) && (value<=64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 193))  ||
                        (value >= 256));
        if (bookTitleContainsIllegalCharts) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
            errors.add(error);
        }
        return  errors;
    }

}
