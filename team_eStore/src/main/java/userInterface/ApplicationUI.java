package userInterface;

import core.services.AddService;
import core.services.FindAllService;
import core.services.FindByIdService;
import core.services.FindByNameService;
import core.services.RemoveByIdService;
import core.services.RemoveByNameService;
import core.services.SearchService;
import core.services.UpdateByIdService;
import core.validators.AddValidator;
import core.validators.FindByIdValidator;
import core.validators.FindByNameValidator;
import core.validators.RemoveByIdValidator;
import core.validators.RemoveByNameValidator;
import core.validators.SearchValidator;
import core.validators.UpdateValidator;
import repository.iDatabase;
import repository.ProductDatabase;

import java.util.Scanner;

public class ApplicationUI implements UIAction {

    private iDatabase database = ProductDatabase.getDataBase();

    private AddService addService = new AddService(database, new AddValidator());
    private FindAllService findAllService = new FindAllService(database);
    private FindByIdService findByIdService = new FindByIdService(database, new FindByIdValidator());
    private FindByNameService findByNameService = new FindByNameService(database, new FindByNameValidator());
    private RemoveByIdService removeByIdService = new RemoveByIdService(database, new RemoveByIdValidator());
    private RemoveByNameService removeByNameService = new RemoveByNameService(database, new RemoveByNameValidator());
    private UpdateByIdService updateInfoService = new UpdateByIdService(database, new UpdateValidator());
    private SearchService searchService = new SearchService(database, new SearchValidator());


    private ExitUIAction exitUIAction = new ExitUIAction();
    private MenuUI menuUI = new MenuUI();
    private AddUIAction addUIAction = new AddUIAction(addService);
    private FindAllUIAction findAllUIAction = new FindAllUIAction(findAllService);
    private FindByIdUIAction findByIdUIAction = new FindByIdUIAction(findByIdService);
    private FindByNameUIAction findByNameUIAction = new FindByNameUIAction(findByNameService);
    private RemoveByIdUIAction removeByIdUIAction = new RemoveByIdUIAction(removeByIdService);
    private RemoveByNameUIAction removeByNameUIAction = new RemoveByNameUIAction(removeByNameService);
    private UpdateUIAction updateUIAction = new UpdateUIAction(updateInfoService);
    private SearchUIAction searchUIAction = new SearchUIAction(searchService);

    @Override
    public void execute() {
        String command;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to start");
        while (!(command = scanner.nextLine()).equals("exit")) {
            menuUI.execute();
            switch (command){
                case "1":
                    addUIAction.execute();
                    break;
                case "2":
                    findAllUIAction.execute();
                    break;
                case "3":
                    findByIdUIAction.execute();
                    break;
                case "4":
                    findByNameUIAction.execute();
                    break;
                case "5":
                    removeByIdUIAction.execute();
                    break;
                case "6":
                    removeByNameUIAction.execute();
                    break;
                case "7":
                    updateUIAction.execute();
                    break;
                case "8":
                    searchUIAction.execute();
                default:
                    exitUIAction.execute();
                    break;
            }
        }
    }
}
