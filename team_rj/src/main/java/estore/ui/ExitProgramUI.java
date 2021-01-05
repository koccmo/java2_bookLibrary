package estore.ui;

import org.springframework.stereotype.Component;

@Component
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
