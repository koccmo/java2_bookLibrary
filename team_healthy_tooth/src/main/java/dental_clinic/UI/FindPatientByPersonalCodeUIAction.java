package dental_clinic.UI;

import dental_clinic.PatientDatabase;
import dental_clinic.domain.PersonalData;

import java.util.List;

class FindPatientByPersonalCodeUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientByPersonalCodeUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){

        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");

        List<PersonalData> result = patientDatabase.findPatientByPersonalCode(personalCode);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with personal code " + personalCode);
        }else{
            result.forEach(System.out::println);
        }

    }

}

