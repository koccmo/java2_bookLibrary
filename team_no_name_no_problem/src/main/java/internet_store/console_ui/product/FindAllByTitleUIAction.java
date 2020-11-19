package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.FindAllByTitleRequest;
import internet_store.core.response.product.FindAllByTitleResponse;
import internet_store.core.services.product.FindAllByTitleService;

import java.util.Scanner;

public class FindAllByTitleUIAction implements UIAction {

    private FindAllByTitleService findAllByTitle;

    public FindAllByTitleUIAction(FindAllByTitleService findAllByTitle) {
        this.findAllByTitle = findAllByTitle;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's title for search: ");
        String title = in.nextLine();

        FindAllByTitleRequest findAllByTitleRequest = new FindAllByTitleRequest(title);
        FindAllByTitleResponse findAllByTitleResponse = findAllByTitle.execute(findAllByTitleRequest);

        if (findAllByTitleResponse.hasErrors()){
            findAllByTitleResponse.getErrors().forEach(System.out::println);
        }else{
            findAllByTitleResponse.getProductList().forEach(System.out::println);
        }
    }

}

