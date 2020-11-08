package dental_clinic.UI;

import dental_clinic.domain.PersonalData;
import dental_clinic.services.AddPatientService;

class AddPatientUIAction implements UIAction {

    private final AddPatientService addPatientService;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddPatientUIAction(AddPatientService addPatientService) {
        this.addPatientService = addPatientService;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter name");
        String surname = inputCheckUtility.inputValidString("Please enter surname");
        String phone = inputCheckUtility.inputValidPhone("Please enter phone");
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");

        PersonalData personalData = new PersonalData(name, surname, phone, personalCode);

        if (addPatientService.addPatient(personalData)){
            System.out.println("Patient added");
        }else{
            System.out.println("The same patient is in database");
        }

    }

}
