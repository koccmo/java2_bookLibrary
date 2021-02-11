package internet_store.application.web_ui.controllers.product;

import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.FindByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindProductByIdController {

    @Autowired private FindByProductIdService findByProductIdService;

    @GetMapping(value = "/product/findProductById")
    public String showFindProductByIdPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindByIdRequest());
        return "product/findProductById";
    }

    @PostMapping("/product/findProductById")
    public String showResultsOfFindProductById(
            @ModelAttribute(value = "request") FindByIdRequest request, ModelMap modelMap){
        FindByProductIdResponse response = findByProductIdService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("cartItem", response.getProductFoundById());
        }
        return "product/findProductById";
    }

}
