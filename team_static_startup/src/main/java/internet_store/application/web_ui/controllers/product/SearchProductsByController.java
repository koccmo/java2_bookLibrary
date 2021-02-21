package internet_store.application.web_ui.controllers.product;

import internet_store.application.core.requests.product.FindProductsRequest;
import internet_store.application.core.requests.product.Ordering;
import internet_store.application.core.responses.product.FindProductsResponse;
import internet_store.application.core.services.product.FindProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchProductsByController {

    @Autowired private FindProductsService findProductsService;

    private Ordering ordering;

    @GetMapping(value = "product/SearchProductBy")
    public String showFindProductsPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindProductsRequest());
        return "product/SearchProductBy";
    }

    @PostMapping("product/SearchProductBy")
    public String searchProductBy(
            @ModelAttribute(value = "request") FindProductsRequest findProductsRequest, ModelMap modelMap){
        FindProductsResponse response = findProductsService.execute(findProductsRequest);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("products", response.getProducts());
        }
        return "product/SearchProductBy";
    }

//    @PostMapping("product/SearchProductBy")
//    public String addOrdering(
//            @ModelAttribute(value = "ordering")  ordering, ModelMap modelMap){
//        modelMap.addAttribute("ordering", ordering);
//
//
//        return "product/SearchProductBy";
//    }


}
