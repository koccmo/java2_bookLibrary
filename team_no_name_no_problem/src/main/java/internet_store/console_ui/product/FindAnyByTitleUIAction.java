package internet_store.console_ui.product;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.FindAnyByTitleRequest;
import internet_store.core.response.product.FindAnyByTitleResponse;
import internet_store.core.services.product.FindAnyByTitleService;

import java.util.Scanner;

public class FindAnyByTitleUIAction implements UIAction {

    private FindAnyByTitleService findAnyByTitleService;

    public FindAnyByTitleUIAction(FindAnyByTitleService findAnyByTitleService) {
        this.findAnyByTitleService = findAnyByTitleService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's title for search: ");
        String title = in.nextLine();

        FindAnyByTitleRequest findAnyByTitleRequest = new FindAnyByTitleRequest(title);
        FindAnyByTitleResponse findAnyByTitleResponse = findAnyByTitleService.execute(findAnyByTitleRequest);

        if (findAnyByTitleResponse.hasErrors()){
            findAnyByTitleResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println(findAnyByTitleResponse.getProduct().get());
        }


    }

}

