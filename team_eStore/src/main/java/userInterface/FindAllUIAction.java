package userInterface;

import core.services.iService;

public class FindAllUIAction implements UIAction{

    private iService findAllService;

    public FindAllUIAction(final iService findAllService) {
        this.findAllService = findAllService;
    }

    @Override
    public void execute() {
        System.out.println("The list of the products store contains: ");
        findAllService.execute(null);
        System.out.println("Type 'Enter' to continue.");
    }
}
