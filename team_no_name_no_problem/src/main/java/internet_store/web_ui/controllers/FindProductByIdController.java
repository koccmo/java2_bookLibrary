package internet_store.web_ui.controllers;


import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.product.SearchProductResponse;
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
    private SearchProductService searchProductService;

    @GetMapping(value = "/findProductById")
    public String showFindProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindByIdRequest());
        return "findProductById";
    }

    @PostMapping("/findProductById")
    public String processFindProductByIDRequest(@ModelAttribute(value = "request") SearchProductRequest searchProductRequest, ModelMap modelMap) {
        SearchProductResponse searchProductResponse = searchProductService.execute(searchProductRequest);
        if (searchProductResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchProductResponse.getErrors());
        } else {
            modelMap.addAttribute("products", searchProductResponse.getProducts());
        }
        return "/findProductById";
    }
}
