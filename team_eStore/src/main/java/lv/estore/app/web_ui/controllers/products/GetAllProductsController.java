package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.GetAllProductsRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllProductsController {

    @Autowired private GetAllProductsService getAllProductsService;


    @GetMapping(value = "products/getAllProducts")
    public String showAllProducts(ModelMap modelMap) {
        GetProductsResponse response = getAllProductsService.execute(
                new GetAllProductsRequest()
        );
        modelMap.addAttribute("products", response.getProducts());
        return "products/getAllProducts";
    }
}
