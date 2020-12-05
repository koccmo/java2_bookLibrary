package dental_clinic.core.services.validators;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DIComponent
public class AddPatientRequestValidator {

    public List <CoreError> validate (AddPatientRequest addPatientRequest){
        List <CoreError> errors = new ArrayList<>();

        PersonalData personalData = addPatientRequest.getPersonalData();
        errors.addAll(isValidPersonalData(personalData));

        return errors;
    }

    private List <CoreError> isValidPersonalData(PersonalData personalData){
        List <CoreError> errors = new ArrayList<>();

        if (!isValidName(personalData.getName())){
            errors.add(new CoreError("Personal data : name", "Not valid input for name"));
        }
        if (!isValidSurname(personalData.getSurname())){
            errors.add(new CoreError("Personal data : surname", "Not valid input for surname"));
        }
        if (!isValidPhone(personalData.getPhone())){
            errors.add(new CoreError("Personal data : phone", "Not valid input for phone"));
        }
        if (!isValidPersonalCode(personalData.getPersonalCode())){
            errors.add(new CoreError("Personal data : personal code", "Not valid input for personal code"));
        }
        return errors;
    }

    private boolean isValidName(String name){
        return name != null && !name.isEmpty();
    }

    private boolean isValidSurname(String surname){
        return surname != null && !surname.isEmpty();
    }

    private boolean isValidPhone(String phone){
        return containsOnlyDigits(phone) && phone.length() == 8;
    }

    private boolean isValidPersonalCode(String personalCode){
        return containsOnlyDigits(personalCode) && personalCode.length() == 11;
    }

    private boolean containsOnlyDigits(String input){
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);
        if (input == null) {
            return false;
        }

        Matcher m = p.matcher(input);

        return m.matches();
    }

}
