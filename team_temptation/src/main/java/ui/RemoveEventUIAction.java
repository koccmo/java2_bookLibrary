package ui;

import core.services.RemoveEventService;

import java.util.Scanner;

public class RemoveEventUIAction implements UIAction {

    private final RemoveEventService removeEventService;

    public RemoveEventUIAction(RemoveEventService removeEventService) {
        this.removeEventService = removeEventService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an event name: ");
        String eventName = scanner.nextLine();
        removeEventService.removeEvent(eventName);
    }
}
