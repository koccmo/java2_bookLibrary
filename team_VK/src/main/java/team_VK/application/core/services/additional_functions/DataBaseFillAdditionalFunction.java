package team_VK.application.core.services.additional_functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.database.DataBaseFiller;

@Component
public class DataBaseFillAdditionalFunction {

    @Autowired(required = false) DataBaseFiller dataBaseFiller; //=context.getBean(DataBaseFiller.class);

    public void execute(){
        if (dataBaseFiller != null) {
            dataBaseFiller.fill();
        }
    }
}
