package estore;

import estore.core.service.*;
import estore.core.validation.*;
import estore.database.ProductCategoryDB;
import estore.database.ProductCategoryDBImpl;
import estore.database.ProductDB;
import estore.database.ProductDBImpl;
import estore.ui.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(UserMenuChoiceValidation.class, new UserMenuChoiceValidation());

        beans.put(AddNewProductValidator.class, new AddNewProductValidator());
        beans.put(RemoveProductByIdValidator.class, new RemoveProductByIdValidator());
        beans.put(RemoveProductByNameValidator.class, new RemoveProductByNameValidator());
        beans.put(SearchProductByNameValidator.class, new SearchProductByNameValidator());
        beans.put(SearchProductByCategoryValidator.class, new SearchProductByCategoryValidator());
        beans.put(AddNewProductCategoryValidator.class, new AddNewProductCategoryValidator());

        beans.put(ProductDB.class, new ProductDBImpl());
        beans.put(ProductCategoryDB.class, new ProductCategoryDBImpl());

        beans.put(AddNewProductService.class,
                new AddNewProductService(getBean(ProductDB.class), getBean(AddNewProductValidator.class)));
        beans.put(RemoveProductByIdService.class,
                new RemoveProductByIdService(getBean(ProductDB.class), getBean(RemoveProductByIdValidator.class)));
        beans.put(RemoveProductByNameService.class,
                new RemoveProductByNameService(getBean(ProductDB.class), getBean(RemoveProductByNameValidator.class)));
        beans.put(SearchProductByNameService.class,
                new SearchProductByNameService(getBean(ProductDB.class), getBean(SearchProductByNameValidator.class)));
        beans.put(SearchProductByCategoryService.class,
                new SearchProductByCategoryService(getBean(ProductDB.class), getBean(SearchProductByCategoryValidator.class)));
        beans.put(ShowAllProductsService.class, new ShowAllProductsService(getBean(ProductDB.class)));
        beans.put(AddNewProductCategoryService.class,
                new AddNewProductCategoryService(getBean(ProductCategoryDB.class), getBean(AddNewProductCategoryValidator.class)));

        beans.put(AddProductUI.class, new AddProductUI(getBean(AddNewProductService.class)));
        beans.put(RemoveProductByIdUI.class, new RemoveProductByIdUI(getBean(RemoveProductByIdService.class)));
        beans.put(RemoveProductByNameUI.class, new RemoveProductByNameUI(getBean(RemoveProductByNameService.class)));
        beans.put(SearchProductByNameUI.class, new SearchProductByNameUI(getBean(SearchProductByNameService.class)));
        beans.put(SearchProductByCategoryUI.class, new SearchProductByCategoryUI(getBean(SearchProductByCategoryService.class)));
        beans.put(ShowAllProductsUI.class, new ShowAllProductsUI(getBean(ShowAllProductsService.class)));
        beans.put(ExitProgramUI.class, new ExitProgramUI());
        beans.put(AddProductCategoryUI.class, new AddProductCategoryUI(getBean(AddNewProductCategoryService.class)));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
