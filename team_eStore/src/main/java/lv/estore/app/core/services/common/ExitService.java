package lv.estore.app.core.services.common;

import org.springframework.stereotype.Component;

@Component
public class ExitService {
    /**
     * Method ot exit app
     */
    public void execute(){
        System.exit(0);
    }
}
