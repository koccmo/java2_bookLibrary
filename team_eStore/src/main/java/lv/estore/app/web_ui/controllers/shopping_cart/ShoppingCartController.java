package lv.estore.app.web_ui.controllers.shopping_cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {

    @GetMapping(value = "/shoppingCart")
    public String showDealsPage() {
        return "shoppingCart";
    }

}
