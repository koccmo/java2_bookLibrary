package internet_store.UI.product;

import internet_store.UI.InputCheckUtility;
import internet_store.UI.UIAction;
import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

import java.util.List;

public class FindAllByTitleUIAction implements UIAction {

    private ProductDatabase productDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAllByTitleUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title for search: ");

        List<Product> result = productDatabase.findAllByTitle(title);
        if (result.size() == 0){
            System.out.println("Product with title " + title +" isn't present in database");
        }else{
            result.forEach(System.out::println);
        }
    }

}

