package internet_store.console_ui.product;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.ChangeDescriptionRequest;
import internet_store.core.response.product.ChangeDescriptionResponse;
import internet_store.core.services.product.ChangeDescriptionService;

import java.util.Scanner;

public class ChangeDescriptionUIAction implements UIAction {

    private ChangeDescriptionService changeDescriptionService;

    public ChangeDescriptionUIAction(ChangeDescriptionService changeDescriptionService) {
        this.changeDescriptionService = changeDescriptionService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's id");
        long id = in.nextLong();

        System.out.println("Please enter new description: ");
        String description = in.nextLine();
        description = in.nextLine();

        ChangeDescriptionRequest changeDescriptionRequest = new ChangeDescriptionRequest(id, description);
        ChangeDescriptionResponse changeDescriptionResponse = changeDescriptionService.execute(changeDescriptionRequest);

        if (changeDescriptionResponse.hasErrors()) {
            changeDescriptionResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Description of product with id " + changeDescriptionResponse.getId() + " was changed");

        }
    }

}

