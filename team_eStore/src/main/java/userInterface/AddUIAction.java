package userInterface;

import core.request.AddRequest;
import core.services.iService;

import java.util.Scanner;

public class AddUIAction implements UIAction{

    private iService addService;

    public AddUIAction(final iService addService) {
        this.addService = addService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product description: ");
        String description = scanner.nextLine();
        System.out.println("Type price: ");
        String price = scanner.nextLine();

        AddRequest addRequest = new AddRequest(name, description, price);
        addService.execute(addRequest);
        System.out.println("Type \"Enter\" to continue.");
    }
}
