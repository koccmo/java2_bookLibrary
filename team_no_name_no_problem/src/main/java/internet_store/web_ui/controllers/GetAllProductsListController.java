package internet_store.web_ui.controllers;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllProductsListController {

    @Autowired
    private GetAllProductsService getAllProductsService;


    @GetMapping(value = "/getAllProductList")
    public String getAllProductList(ModelMap modelMap) {
        GetProductsResponse response = getAllProductsService.execute(new GetProductsRequest()
        );
        modelMap.addAttribute("products", response.getProducts());
        return "/getAllProductList";
    }

}
