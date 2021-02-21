package internet_store.web_ui.controllers;


import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.order.GetOrdersResponse;
import internet_store.core.services.order.GetOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetOrdersController {

    @Autowired
    private GetOrdersService getOrdersService;

    @GetMapping(value = "/getOrders")
    public String getOrdersList(ModelMap modelMap) {
        GetOrdersResponse getOrdersResponse = getOrdersService.execute(new GetOrdersRequest());
        modelMap.addAttribute("orders", getOrdersResponse.getOrders());
        return "/getOrders";
    }
}
