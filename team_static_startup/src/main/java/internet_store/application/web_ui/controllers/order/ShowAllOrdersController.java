package internet_store.application.web_ui.controllers.order;

import internet_store.application.core.responses.order.FindAllOrdersResponse;
import internet_store.application.core.services.order.FindAllOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllOrdersController {

    @Autowired
    private FindAllOrdersService findAllOrdersService;

    @GetMapping(value = "order/showAllOrders")
    public String showAllOrdersPage(ModelMap modelMap){
        FindAllOrdersResponse response = findAllOrdersService.execute();
        modelMap.addAttribute("orders", response.getOrders());
        return "order/showAllOrders";
    }

}
