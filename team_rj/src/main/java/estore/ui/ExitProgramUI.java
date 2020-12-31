package estore.ui;

import estore.dependency_injection.DIComponent;

@DIComponent
public class ExitProgramUI implements UIAction {

    @Override
    public void execute() {
        this.printGoodBye();
        System.exit(0);
    }

    private void printGoodBye() {
        System.out.println("");
        System.out.println("Thanks for visiting RedDots!");
    }
}
