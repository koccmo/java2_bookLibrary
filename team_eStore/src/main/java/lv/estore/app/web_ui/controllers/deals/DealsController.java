package lv.estore.app.web_ui.controllers.deals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DealsController {

    @GetMapping(value = "/deals")
    public String showDealsPage() {
        return "deals/deals";
    }

}
