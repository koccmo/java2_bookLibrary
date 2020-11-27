package dental_clinic.console_ui;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.services.FindPatientsByPersonalCodeService;

import java.util.List;

class FindPatientByPersonalCodeUIAction implements UIAction {

    private FindPatientsByPersonalCodeService findPatientByPersonalCode;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientByPersonalCodeUIAction(FindPatientsByPersonalCodeService findPatientByPersonalCode) {
        this.findPatientByPersonalCode = findPatientByPersonalCode;
    }

    public void execute(){
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");

        List<Patient> result = findPatientByPersonalCode.execute(personalCode);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with personal code " + personalCode);
        }else{
            result.forEach(System.out::println);
        }
    }

}

