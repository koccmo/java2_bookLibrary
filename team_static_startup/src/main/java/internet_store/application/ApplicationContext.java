package internet_store.application;

import internet_store.application.console_ui.*;
import internet_store.application.core.database.*;
import internet_store.application.core.services.*;
import internet_store.application.core.services.validators.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabase());

        beans.put(AddProductValidator.class, new AddProductValidator());
        beans.put(DeleteByProductIdValidator.class, new DeleteByProductIdValidator());
        beans.put(DeleteByProductValidator.class, new DeleteByProductValidator());
        beans.put(DeleteByProductNameValidator.class, new DeleteByProductNameValidator());
        beans.put(FindByIdValidator.class, new FindByIdValidator());
        beans.put(FindProductsRequestValidator.class, new FindProductsRequestValidator());
        beans.put(ChangeProductNameValidator.class, new ChangeProductNameValidator());

        beans.put(AddProductService.class, new AddProductService(
                getBean(Database.class), getBean(AddProductValidator.class)));
        beans.put(DeleteByProductIdService.class, new DeleteByProductIdService(
                getBean(Database.class), getBean(DeleteByProductIdValidator.class)));
        beans.put(DeleteProductByProductService.class, new DeleteProductByProductService(
                getBean(Database.class), getBean(DeleteByProductValidator.class)));
        beans.put(DeleteByProductNameService.class, new DeleteByProductNameService(
                getBean(Database.class), getBean(DeleteByProductNameValidator.class)));
        beans.put(FindByIdService.class, new FindByIdService(
                getBean(Database.class), getBean(FindByIdValidator.class)));
        beans.put(FindProductsService.class, new FindProductsService(
                getBean(Database.class), getBean(FindProductsRequestValidator.class)));
        beans.put(ChangeProductNameService.class, new ChangeProductNameService(
                getBean(Database.class), getBean(ChangeProductNameValidator.class)));
        beans.put(GetAllProductsService.class, new GetAllProductsService(getBean(Database.class)));

        beans.put(AddProductUIAction.class, new AddProductUIAction(getBean(AddProductService.class)));
        beans.put(DeleteByIdUIAction.class, new DeleteByIdUIAction(getBean(DeleteByIdUIAction.class)));
        beans.put(DeleteByProductUIAction.class, new DeleteByProductUIAction(
                getBean(DeleteProductByProductService.class)));
        beans.put(DeleteByProductNameUIAction.class, new DeleteByProductNameUIAction(
                getBean(DeleteByProductNameService.class)));
        beans.put(FindByIdUIAction.class, new FindByIdUIAction(
                getBean(FindByIdService.class)));
        beans.put(FindProductsUIAction.class, new FindProductsUIAction(
                getBean(FindProductsService.class)));
        beans.put(ChangeProductNameUIAction.class, new ChangeProductNameUIAction(
                getBean(ChangeProductNameService.class), getBean(FindByIdService.class)));
        beans.put(GetAllProductsUIAction.class, new GetAllProductsUIAction(
                getBean(GetAllProductsService.class)));
        beans.put(ExitProgramUIAction.class, new ExitProgramUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
