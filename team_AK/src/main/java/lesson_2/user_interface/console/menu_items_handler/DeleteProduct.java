package lesson_2.user_interface.console.menu_items_handler;

import lesson_1.menu.DeleteMenu;
import lesson_1.user_handler.UserInputValue;

public class DeleteProduct {

    public long deleteCommandHandler() {
        DeleteMenu deleteMenu = new DeleteMenu();

        deleteMenu.showDeleteMenu();
        UserInputValue<?> userInputValue = deleteMenu.getUserInputValue();
        return (long) userInputValue.getValue();
    }
}