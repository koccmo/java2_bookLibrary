package dental_clinic.console_ui;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.services.FindPatientBySurnameService;

import java.util.List;

class FindPatientBySurnameUIAction implements UIAction {

    private FindPatientBySurnameService findPatientBySurname;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientBySurnameUIAction(FindPatientBySurnameService findPatientBySurname) {
        this.findPatientBySurname = findPatientBySurname;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter surname");

        List<Patient> result = findPatientBySurname.execute(surname);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with surname " + surname);
        }else{
            result.forEach(System.out::println);
        }

    }

}

