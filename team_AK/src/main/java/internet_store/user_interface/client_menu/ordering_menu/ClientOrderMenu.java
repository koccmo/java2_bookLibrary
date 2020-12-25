package internet_store.user_interface.client_menu.ordering_menu;

import org.springframework.stereotype.Component;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class ClientOrderMenu {
    @Getter
    private long userOrderClientIdInput;

    public void showMenuOrderClientId() {
        boolean errorInput;
        System.out.println("Select client Id for ordering");
        System.out.println("Enter ID number");
        do {
            try {
                userOrderClientIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}
