package dental_clinic.console_ui.ProgramMenu;

import dental_clinic.console_ui.*;
import dental_clinic.console_ui.doctor.AddDoctorUIAction;
import dental_clinic.console_ui.doctor.DeleteDoctorUIAction;
import dental_clinic.console_ui.patient.*;
import dental_clinic.console_ui.visit.AddVisitUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToActionMap = new HashMap<>();

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToActionMap.put(1, findUIAction(uiActions, AddPatientUIAction.class));
        menuNumberToActionMap.put(2, findUIAction(uiActions, ChangePersonalDataUiAction.class));
        menuNumberToActionMap.put(3, findUIAction(uiActions, SearchPatientByPersonalCodeUIAction.class));
        menuNumberToActionMap.put(4, findUIAction(uiActions, SearchPatientUIAction.class));
        menuNumberToActionMap.put(5, findUIAction(uiActions, DeletePatientUIAction.class));

        menuNumberToActionMap.put(6, findUIAction(uiActions, GetAllPatientsUIAction.class));
        menuNumberToActionMap.put(7, findUIAction(uiActions, GetSpecificPatientHistoryUIAction.class));

        menuNumberToActionMap.put(8, findUIAction(uiActions, GetPatientCardUIAction.class));
        menuNumberToActionMap.put(9, findUIAction(uiActions, AddVisitUIAction.class));

        menuNumberToActionMap.put(10, findUIAction(uiActions, AddDoctorUIAction.class));
        menuNumberToActionMap.put(11, findUIAction(uiActions, DeleteDoctorUIAction.class));

        menuNumberToActionMap.put(0, findUIAction(uiActions, ExitUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void executeMenuUIAction(int selectedMenuNumber) {
        menuNumberToActionMap.get(selectedMenuNumber).execute();
    }

    public static int inputValidInteger(){
        Integer input;
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Please enter menu number: ");
            while (true){
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }
            if (input >= 0){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }

    public void print(){
        System.out.println("\n                Menu\n\n" +
                "       1   Add patient\n" +
                "       2   Change patient's personal data\n" +
                "       3   Search patient by personal code\n" +
                "       4   Search by name / surname\n" +
                "       5   Delete by id\n\n" +

                "       6   Print patients database\n" +
                "       7   Print specific patient information\n\n" +

                "       8   Print patients card\n" +
                "       9   Add visit\n\n" +

                "       10   Add doctor\n" +
                "       11   Delete doctor\n\n" +

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
