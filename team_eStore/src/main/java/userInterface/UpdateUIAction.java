package userInterface;

import core.request.UpdateRequest;
import core.services.iService;

import java.util.Scanner;

public class UpdateUIAction implements UIAction {

    private iService updateInfoService;

    public UpdateUIAction(final iService updateInfoService) {
        this.updateInfoService = updateInfoService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type ID of the product you want to update info: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.println("Type the updated product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product updated description: ");
        String description = scanner.nextLine();
        System.out.println("Type updated price: ");
        String price = scanner.nextLine();

        UpdateRequest updateRequest = new UpdateRequest(id, name, description, price);
        updateInfoService.execute(updateRequest);
        System.out.println("Type 'Enter'' to continue.");
    }
}
