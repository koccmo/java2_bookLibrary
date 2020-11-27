package online_schedule.ui;

import online_schedule.core.domain.PersonalData;
import online_schedule.core.service.AddClientService;
import online_schedule.core.service.AddMasterListService;

public class AddMasterListUIAction {

    private final AddMasterListService addMasterListService;

    InputUtility inputUtility = new InputUtility();


    public AddMasterListUIAction(AddMasterListService addMasterListService){
        this.addMasterListService = addMasterListService;
    }


}
