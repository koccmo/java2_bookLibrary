package lv.estore.app.web_ui.controllers.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @GetMapping(value = "/products")
    public String showProductsPage() {
        return "products/products";
    }

}
