package team_VK.application.ui.additional_function;

import org.springframework.stereotype.Component;
import team_VK.application.core.responses.CoreResponse;

@Component
public class ErrorsPrinter {

    public void execute(CoreResponse response) {
        response.getErrorList().forEach(coreError -> System.out.println(coreError.toString()));
        System.out.println("----------------------");
        System.out.println();
    }
}
