package online_schedule.ui;

import online_schedule.core.domain.PersonalData;
import online_schedule.core.service.AddClientService;

public class AddClientUIAction {

    private final AddClientService addClientService;

    InputUtility inputUtility = new InputUtility();


    public AddClientUIAction(AddClientService addClientService){
        this.addClientService = addClientService;
    }

    public void execute(){
        String name = inputUtility.inputValidString(" Please enter your name!");
        String surname = inputUtility.inputValidString("Please enter your surname");
        String mobileNumber = inputUtility.inputValidMobileNumber("Please enter your mobile number");

        PersonalData personalData = new PersonalData(name, surname, mobileNumber);

        if(addClientService.addClient(personalData)){
            System.out.println("Client added!");
        }else {
            System.out.println("The same client is in database");
        }
    }


}
