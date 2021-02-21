package internet_store.web_ui.controllers;


import internet_store.core.requests.product.SearchProductByOtherRequest;
import internet_store.core.response.product.SearchProductByOtherResponse;
import internet_store.core.services.product.SearchProductByOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchProductController {

    @Autowired
    private SearchProductByOtherService searchProductService;

    @GetMapping(value = "/searchProduct")
    public String showSearchProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchProductByOtherRequest());
        return "searchProduct";
    }

    @PostMapping("/searchProduct")
    public String processSearchProductRequest(@ModelAttribute(value = "request") SearchProductByOtherRequest searchProductRequest, ModelMap modelMap) {
        SearchProductByOtherResponse searchProductResponse = searchProductService.execute(searchProductRequest);
        if (searchProductResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchProductResponse.getErrors());
        } else {
            modelMap.addAttribute("products", searchProductResponse.getProducts());
        }
         return "/searchProduct";
    }
}
