package internet_store.application.web_ui.controllers.order;

import internet_store.application.core.requests.order.FindOrderByIdRequest;
import internet_store.application.core.responses.order.FindOrderByIdResponse;
import internet_store.application.core.services.order.FindOrderByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindOrderById {

    @Autowired FindOrderByIdService findOrderByIdService;

    @GetMapping(value = "order/findOrderById")
    public String showFindOrderByIdPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindOrderByIdRequest());
        return "order/findOrderById";
    }

    @PostMapping("order/findOrderById")
    public String findOrderById(
            @ModelAttribute(value = "request") FindOrderByIdRequest request, ModelMap modelMap){
        FindOrderByIdResponse response = findOrderByIdService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("orders", response.getOrder());
        }
        return "order/findOrderById";
    }

}
