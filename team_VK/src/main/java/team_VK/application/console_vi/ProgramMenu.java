package team_VK.application.console_vi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.main_menu.*;

import java.text.ParseException;
import java.util.*;

@Component
public class ProgramMenu {

    private Map<Integer, UIActions> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIActions> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddBookUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, RemoveBookUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, BookSearchAndBookMenuUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, ShowBookUIActions.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, BookBookUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, AddClientUIActions.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, ExitProgramUIAction.class));
    }

    private UIActions findUIAction(List<UIActions> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println();
        System.out.println("Please select your action.");
        System.out.println("1. Add new book.");
        System.out.println("2. Delete book.");
        System.out.println("3. Search book and make booking.");
        System.out.println("4. Show book parameters by ID.");
        System.out.println("5. Book book.");
        System.out.println("6. Register new Client.");
        System.out.println("7. Exit program.");
        System.out.println();
    }

    public static int userChoiceWithPossibleException() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String choiceString = scanner.nextLine();
        Optional<Integer> choice = Optional.of(Integer.valueOf(choiceString));
        if (choice.isPresent())
            return choice.get();
        else return 0;
    }

    public void executeSelectedMenuItem(int selectedMenu) throws ParseException {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }

}
