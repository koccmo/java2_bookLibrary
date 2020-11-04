package lesson_1.command_handler;

import lesson_1.menu.DeleteMenu;
import lesson_1.user_handler.UserInputValue;

public class DeleteProductCommand {

    public long deleteCommandHandler() {
        DeleteMenu deleteMenu = new DeleteMenu();

        deleteMenu.showDeleteMenu();
        UserInputValue<?> userInputValue = deleteMenu.getUserInputValue();
        return (long) userInputValue.getValue();
    }
}
