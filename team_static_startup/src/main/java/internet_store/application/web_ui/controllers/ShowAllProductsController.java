package internet_store.application.web_ui.controllers;

import internet_store.application.core.responses.product.GetAllProductsResponse;
import internet_store.application.core.services.product.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public class ShowAllProductsController {

    @Autowired
    private GetAllProductsService getAllProductsService;

    @GetMapping(value = "/showAllProducts")
    public String showAllProducts(ModelMap modelMap) {
//        GetAllProductsResponse response = getAllProductsService.execute();
//        modelMap.addAttribute("products", response.getProductList());
        return "/showAllProducts";
    }

}
