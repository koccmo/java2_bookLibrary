package internet_store.web_ui.controllers;


import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.product.FindProductByIdResponse;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.FindProductByIdService;
import internet_store.core.services.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindProductByIdController {

    @Autowired
    private FindProductByIdService findProductByIdService;

    @GetMapping(value = "/findProductById")
    public String showFindProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindProductByIdRequest());
        return "findProductById";
    }

    @PostMapping("/findProductById")
    public String processFindProductByIDRequest(@ModelAttribute(value = "request") FindProductByIdRequest findProductByIdRequest, ModelMap modelMap) {
        FindProductByIdResponse findProductByIdResponse = findProductByIdService.execute(findProductByIdRequest);
        if (findProductByIdResponse.hasErrors()) {
            modelMap.addAttribute("errors", findProductByIdResponse.getErrors());
        } else {
            modelMap.addAttribute("products", findProductByIdResponse.getProducts());
        }
        return "/findProductById";
    }
}
