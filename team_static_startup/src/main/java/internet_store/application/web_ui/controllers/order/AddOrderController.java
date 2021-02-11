package internet_store.application.web_ui.controllers.order;

import internet_store.application.core.requests.order.AddOrderRequest;
import internet_store.application.core.responses.order.AddOrderResponse;
import internet_store.application.core.services.order.AddOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddOrderController {

    @Autowired
    private AddOrderService addOrderService;

    @GetMapping(value = "/order/addOrder")
    public String showAddOrderPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddOrderRequest());
        return "order/addOrder";
    }

    @PostMapping("/order/addOrder")
    public String processAddOrderRequest(@ModelAttribute(value = "request") AddOrderRequest request, ModelMap modelMap) {
        AddOrderResponse response = addOrderService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "order/addOrder";
        } else {
            return "redirect:/";
        }
    }

}