package internet_store.web_ui.controllers;


import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.response.product.SearchProductByIdResponse;
import internet_store.core.services.product.SearchProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchProductByIdController {

    @Autowired
    private SearchProductByIdService findProductByIdService;

    @GetMapping(value = "/findProductById")
    public String showFindProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchProductByIdRequest());
        return "findProductById";
    }

    @PostMapping("/findProductById")
    public String processFindProductByIDRequest(@ModelAttribute(value = "request") SearchProductByIdRequest findProductByIdRequest, ModelMap modelMap) {
        SearchProductByIdResponse findProductByIdResponse = findProductByIdService.execute(findProductByIdRequest);
        if (findProductByIdResponse.hasErrors()) {
            modelMap.addAttribute("errors", findProductByIdResponse.getErrors());
        } else {
            modelMap.addAttribute("products", findProductByIdResponse.getProducts());
        }
        return "/findProductById";
    }
}
