package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.product.ProductDatabase;
import internet_store.services.product.FindAnyByTitleService;

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

