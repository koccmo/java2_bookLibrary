package lesson_2.console_ui;

import lesson_2.services.ChangeTargetDeadlineService;

import java.util.Scanner;

public class ChangeTargetDeadlineUIAction implements UIAction {

    private final ChangeTargetDeadlineService changeTargetDeadlineService;

    public ChangeTargetDeadlineUIAction(ChangeTargetDeadlineService changeTargetDeadlineService){
        this.changeTargetDeadlineService = changeTargetDeadlineService;
    }

    @Override
    public void execute() {
        Scanner scr = new Scanner(System.in);
        System.out.print("Enter target ID: ");
        Long targetId = Long.parseLong(scr.nextLine());
        System.out.print("Enter new target deadline: ");
        int newTargetDeadline = Integer.parseInt(scr.nextLine());
        changeTargetDeadlineService.execute(targetId, newTargetDeadline);
        System.out.println("----------");
        System.out.println("Target deadline was changed!");
        System.out.println("----------");
    }

}
