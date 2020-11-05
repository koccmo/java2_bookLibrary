package internet_store.UI;

import internet_store.database.ProductDatabase;

class FindAnyByTitleUIAction implements UIAction {

    private ProductDatabase productDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindAnyByTitleUIAction(ProductDatabase itemDatabase){
        this.productDatabase = itemDatabase;
    }

    public void execute(){
        String title = inputCheckUtility.inputValidString("Please enter product's title for search: ");

        if (productDatabase.findAnyByTitle(title).isPresent()){
            System.out.println(productDatabase.findAnyByTitle(title).get());
        }else{
            System.out.println("Product with title " + title +" isn't present in database!");
        }

    }

}

