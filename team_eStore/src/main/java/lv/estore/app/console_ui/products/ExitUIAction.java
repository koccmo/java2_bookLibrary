package lv.estore.app.console_ui.products;

import lv.estore.app.core.services.common.ExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction{

    @Autowired
    ExitService exitService;
    /**
     * Method ot exit app
     */
    public void execute(){
        System.out.println("Application closed.");
        exitService.execute();
    }

}
