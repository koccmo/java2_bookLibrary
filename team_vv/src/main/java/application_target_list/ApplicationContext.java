package application_target_list;


import application_target_list.console_ui.actions.*;
import application_target_list.core.database.Database;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.services.*;
import application_target_list.core.validators.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();


    public ApplicationContext() {
        beans.put(Database.class, new TargetListImpl());

        beans.put(AddTargetValidator.class,  new AddTargetValidator());
        beans.put(DeleteTargetValidator.class, new DeleteTargetValidator());
        beans.put(ChangeTargetNameValidator.class, new ChangeTargetNameValidator());
        beans.put(ChangeTargetDescriptionValidator.class, new ChangeTargetDescriptionValidator());
        beans.put(ChangeTargetDeadlineValidator.class, new ChangeTargetDeadlineValidator());
        beans.put(SearchTargetByNameValidator.class, new SearchTargetByNameValidator());
        beans.put(SearchTargetByDescriptionValidator.class, new SearchTargetByDescriptionValidator());


        beans.put(GetAllTargetsService.class, new GetAllTargetsService(getBean(Database.class)));
        beans.put(AddTargetService.class, new AddTargetService(getBean(Database.class),
                getBean(AddTargetValidator.class)));
        beans.put(DeleteTargetService.class, new DeleteTargetService(getBean(Database.class),
                getBean(DeleteTargetValidator.class)));
        beans.put(ChangeTargetNameService.class, new ChangeTargetNameService(getBean(Database.class),
                getBean(ChangeTargetNameValidator.class)));
        beans.put(ChangeTargetDescriptionService.class, new ChangeTargetDescriptionService(
                getBean(Database.class),
                getBean(ChangeTargetDescriptionValidator.class)));
        beans.put(ChangeTargetDeadlineService.class, new ChangeTargetDeadlineService(
                getBean(Database.class),
                getBean(ChangeTargetDeadlineValidator.class)));
        beans.put(SearchTargetByNameService.class, new SearchTargetByNameService(
                getBean(Database.class),
                getBean(SearchTargetByNameValidator.class)));
        beans.put(SearchTargetByDescriptionService.class, new SearchTargetByDescriptionService(
                getBean(Database.class),
                getBean(SearchTargetByDescriptionValidator.class)));

        beans.put(MenuUIAction.class, new MenuUIAction());
        beans.put(TargetChangesMenuUIAction.class, new TargetChangesMenuUIAction());
        beans.put(ChangeTargetDeadlineUIAction.class, new ChangeTargetDeadlineUIAction(
                getBean(ChangeTargetDeadlineService.class)));
        beans.put(ChangeTargetDescriptionUIAction.class, new ChangeTargetDescriptionUIAction(
                getBean(ChangeTargetDescriptionService.class)));
        beans.put(ChangeTargetNameUIAction.class, new ChangeTargetNameUIAction(
                getBean(ChangeTargetNameService.class)));
        beans.put(DeleteUIAction.class, new DeleteUIAction(
                getBean(DeleteTargetService.class)));
        beans.put(AddTargetUIAction.class, new AddTargetUIAction(
                getBean(AddTargetService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
        beans.put(GetAllTargetsUIAction.class, new GetAllTargetsUIAction(
                getBean(GetAllTargetsService.class)));
        beans.put(SearchTargetByNameUIAction.class, new SearchTargetByNameUIAction(
                getBean(SearchTargetByNameService.class)));
        beans.put(SearchTargetByDescriptionUIAction.class, new SearchTargetByDescriptionUIAction(
                getBean(SearchTargetByDescriptionService.class)));
        beans.put(SearchTargetMenuUIAction.class, new SearchTargetMenuUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
