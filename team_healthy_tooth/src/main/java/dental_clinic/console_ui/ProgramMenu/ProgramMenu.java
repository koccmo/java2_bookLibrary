package dental_clinic.console_ui.ProgramMenu;

import dental_clinic.console_ui.*;
import dental_clinic.console_ui.doctor.AddDoctorUIAction;
import dental_clinic.console_ui.doctor.DeleteDoctorUIAction;
import dental_clinic.console_ui.doctor.GetDoctorListUIAction;
import dental_clinic.console_ui.patient.*;
import dental_clinic.console_ui.planned_visit.*;
import dental_clinic.console_ui.visit.AddVisitUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToActionMap = new HashMap<>();

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToActionMap.put(1, findUIAction(uiActions, AddPatientUIAction.class));
        menuNumberToActionMap.put(2, findUIAction(uiActions, ChangePersonalDataUiAction.class));
        menuNumberToActionMap.put(3, findUIAction(uiActions, SearchPatientUIAction.class));
        menuNumberToActionMap.put(4, findUIAction(uiActions, DeletePatientUIAction.class));

        menuNumberToActionMap.put(5, findUIAction(uiActions, GetAllPatientsUIAction.class));
        menuNumberToActionMap.put(6, findUIAction(uiActions, GetSpecificPatientHistoryUIAction.class));

        menuNumberToActionMap.put(7, findUIAction(uiActions, GetPatientCardUIAction.class));
        menuNumberToActionMap.put(8, findUIAction(uiActions, AddVisitUIAction.class));

        menuNumberToActionMap.put(9, findUIAction(uiActions, AddDoctorUIAction.class));
        menuNumberToActionMap.put(10, findUIAction(uiActions, DeleteDoctorUIAction.class));
        menuNumberToActionMap.put(11, findUIAction(uiActions, GetDoctorListUIAction.class));

        menuNumberToActionMap.put(12, findUIAction(uiActions, AddPlannedVisitUIAction.class));
        menuNumberToActionMap.put(13, findUIAction(uiActions, ChangePlannedVisitTimeUIAction.class));
        menuNumberToActionMap.put(14, findUIAction(uiActions, GetPlannedVisitsUIAction.class));
        menuNumberToActionMap.put(15, findUIAction(uiActions, CancelPlannedVisitUIAction.class));
        menuNumberToActionMap.put(16, findUIAction(uiActions, SearchPlannedVisitsByPersonalCodeUIAction.class));
        menuNumberToActionMap.put(17, findUIAction(uiActions, SearchPlannedVisitsByDateUIAction.class));

        menuNumberToActionMap.put(0, findUIAction(uiActions, ExitUIAction.class));
    }
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void executeMenuUIAction(int selectedMenuNumber) {
        menuNumberToActionMap.get(selectedMenuNumber).execute();
    }



    public int inputValidInteger(){
        return inputFormatsValidator.inputInteger("Please enter menu number: ");
    }

    public void print(){
        System.out.println("\n                Menu\n\n" +
                "       1   Add patient\n" +
                "       2   Change patient's personal data\n" +
                "       3   Search by surname or personal code\n" +
                "       4   Delete by id\n\n" +

                "       5   Print patients database\n" +
                "       6   Print specific patient information\n\n" +

                "       7   Print patients card\n" +
                "       8   Add visit\n\n" +

                "       9   Add doctor\n" +
                "       10   Delete doctor\n" +
                "       11   Print doctor's database\n\n" +

                "       12   Add planned visit\n" +
                "       13   Change planned visit\n" +
                "       14   Print planned visits\n" +
                "       15   Cancel planned visit\n" +
                "       16   Search planned visit by personal code\n" +
                "       17   Search planned visit by date\n\n" +

                "       0   Exit\n");
    }

    public void printLogo(){
        System.out.println("\n\n" +
                "  ###  ###   ######     ##     ###      ######   ###  ###   ##  ##         ######     ####        ####     ######   ###  ###               \n" +
                "  ###  ###   ###      ######   ###        ##     ###  ###   ##  ##           ##      ######      ######      ##     ###  ###       ##  ##      \n" +
                "  ########   #####   ##   ##   ###        ##     ########    ####            ##     ##    ##    ##    ##     ##     ########                \n" +
                "  ###  ###   ###     #######   ###        ##     ###  ###     ##             ##      ######      ######      ##     ###  ###      ##   ##\n" +
                "  ###  ###   #####   ##   ##   ######     ##     ###  ###     ##             ##       ####        ####       ##     ###  ###       #####");
    }
}
