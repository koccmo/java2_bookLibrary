package dental_clinic.core.services;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.LinkedList;
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
        // 123456-12345 OR 12345612345
        return Pattern.matches("/d{6}/-?/d{5}", personalCode);
    }

}
