package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FindPatientByPersonalCodeValidator {

    public List<CoreError> validate (FindPatientByPersonalCodeRequest request) {
        List<CoreError> errorsResult = new ArrayList<>();

        String personalCodeToSearch = request.getPersonalCodeToSearch();

        if (!isRegexCorrect(personalCodeToSearch)) {
            errorsResult.add(new CoreError("Personal data : personal code",
                    "Invalid input for personal code!"));
        }
        return errorsResult;
    }

    private boolean isRegexCorrect(String personalCode) {
        return Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", personalCode);

    }

}
