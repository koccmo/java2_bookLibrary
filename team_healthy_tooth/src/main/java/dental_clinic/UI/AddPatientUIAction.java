package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.PersonalData;
import dental_clinic.services.AddPatientServices;

class AddPatientUIAction implements UIAction {

    private final AddPatientServices addPatientServices;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddPatientUIAction(AddPatientServices addPatientServices) {
        this.addPatientServices = addPatientServices;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter name");
        String surname = inputCheckUtility.inputValidString("Please enter surname");
        String phone = inputCheckUtility.inputValidPhone("Please enter phone");
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");
        String doctor = inputCheckUtility.inputValidString("Please enter doctor");

        PersonalData personalData = new PersonalData(name, surname, phone, personalCode);

        if (addPatientServices.addPatient(personalData)){
            System.out.println("Patient added");
        }else{
            System.out.println("The same patient is in database");
        }

    }

}
