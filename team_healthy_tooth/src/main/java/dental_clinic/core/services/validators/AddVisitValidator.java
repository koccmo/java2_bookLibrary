package dental_clinic.core.services.validators;

import dental_clinic.core.requests.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class AddVisitValidator {

    public List<CoreError> validate (AddVisitRequest addVisitRequest){

        List <CoreError> coreErrors = new ArrayList<>();

        if ((addVisitRequest.getId() == null) || (addVisitRequest.getId() < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }

        if (!isValidToothNumber(addVisitRequest.getToothNumber())){
            coreErrors.add(new CoreError("tooth number", "Not valid input for tooth number"));
        }

        if (addVisitRequest.getDoctor() == null || addVisitRequest.getDoctor().isEmpty()){
            coreErrors.add(new CoreError("doctor", "Not valid input for doctor"));
        }

        return coreErrors;

    }


    private boolean isValidToothNumber(int number){
        return (number >=11 && number <= 18) ||
                (number >=21 && number <= 28) ||
                (number >= 31 && number <=38) ||
                (number >= 41 && number <= 48) ||
                (number >= 51 && number <= 55) ||
                (number >= 61 && number <= 65) ||
                (number >= 71 && number <= 75) ||
                (number >= 81 && number <= 85);
    }
}
