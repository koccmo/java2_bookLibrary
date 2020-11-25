package internet_store_1.console_ui.product;

import internet_store_1.console_ui.UIAction;
import internet_store_1.core.requests.product.ChangeTitleRequest;
import internet_store_1.core.response.product.ChangeTitleResponse;
import internet_store_1.core.services.product.ChangeTitleService;

import java.util.Scanner;

public class ChangeTitleUIAction implements UIAction {

    private ChangeTitleService changeTitle;

    public ChangeTitleUIAction(ChangeTitleService changeTitle) {
        this.changeTitle = changeTitle;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's id");
        long id = in.nextLong();

        System.out.println("Please enter new title");
        String title = in.nextLine();

        ChangeTitleRequest changeTitleRequest = new ChangeTitleRequest(id, title);
        ChangeTitleResponse changeTitleResponse = changeTitle.execute(changeTitleRequest);

        if (changeTitleResponse.hasErrors()) {
            changeTitleResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Title of product with id " + changeTitleRequest.getId() + " was changed");
        }
    }

}

