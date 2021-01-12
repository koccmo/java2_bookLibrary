package lv.estore.app.userInterface;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction{

    /**
     * Method ot exit app
     */
    public void execute(){
        System.out.println("Application closed.");
        System.exit(0);
    }

}
