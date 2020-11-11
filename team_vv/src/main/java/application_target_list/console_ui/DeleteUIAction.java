package application_target_list.console_ui;

import application_target_list.services.DeleteTargetService;

import java.util.Scanner;

public class DeleteUIAction implements UIAction{

    private DeleteTargetService deleteTargetService;

    public DeleteUIAction(DeleteTargetService deleteTargetService){
        this.deleteTargetService = deleteTargetService;
    }

    @Override
    public void execute() {
        Scanner scr = new Scanner(System.in);
        System.out.print("Enter target ID: ");
        Long targetId = Long.parseLong(scr.nextLine());
        deleteTargetService.execute(targetId);
        System.out.println("----------");
        System.out.println("Target was deleted!");
        System.out.println("----------");
    }


}
