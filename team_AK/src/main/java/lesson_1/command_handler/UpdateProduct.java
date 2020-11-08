package lesson_1.command_handler;

import lesson_1.menu.UpdateMenu;
import lesson_1.user_handler.UserInputValue;

public class UpdateProduct {

    public long updateProductCommand() {
        UpdateMenu updateMenu = new UpdateMenu();

        updateMenu.showDeleteMenu();
        UserInputValue<?> userInputValue = updateMenu.getUserInputValue();
        return (long) userInputValue.getValue();
    }
}
