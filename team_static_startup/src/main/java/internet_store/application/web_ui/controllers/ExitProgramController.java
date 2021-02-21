package internet_store.application.web_ui.controllers;

import internet_store.application.core.services.ExitProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExitProgramController {

    @Autowired
    private ExitProgramService exitProgramService;

    @GetMapping(value = "/exitProgram")
    public String exitProgram(ModelMap modelMap) {
        exitProgramService.execute();
        return "exitProgram";
    }

}
