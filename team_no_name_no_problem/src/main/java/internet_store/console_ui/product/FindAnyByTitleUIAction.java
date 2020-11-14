package internet_store.console_ui.product;

import internet_store.console_ui.InputCheckUtility;
import internet_store.console_ui.UIAction;
import internet_store.core.services.product.FindAnyByTitleService;

public class FindAnyByTitleUIAction implements UIAction {

    private FindAnyByTitleService findAnyByTitleService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAnyByTitleUIAction(FindAnyByTitleService findAnyByTitleService) {
        this.findAnyByTitleService = findAnyByTitleService;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title for search: ");

        if (findAnyByTitleService.execute(title).isPresent()){
            System.out.println(findAnyByTitleService.execute(title).get());
        }else{
            System.out.println("Product with title " + title +" isn't present in database!");
        }

    }

}

