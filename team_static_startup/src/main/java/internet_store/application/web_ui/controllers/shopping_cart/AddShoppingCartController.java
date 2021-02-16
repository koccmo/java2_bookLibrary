package internet_store.application.web_ui.controllers.shopping_cart;

import internet_store.application.core.requests.shopping_cart.AddShoppingCartRequest;
import internet_store.application.core.responses.shopping_cart.AddShoppingCartResponse;
import internet_store.application.core.services.shopping_cart.AddShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddShoppingCartController {

    @Autowired
    private AddShoppingCartService addShoppingCartService;

    @GetMapping(value = "/shopping_cart/addShoppingCart")
    public String showAddShoppingCartPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddShoppingCartRequest());
        return "shopping_cart/addShoppingCart";
    }

    @PostMapping("/shopping_cart/addShoppingCart")
    public String processAddShoppingCartRequest(@ModelAttribute(value = "request") AddShoppingCartRequest request, ModelMap modelMap) {
        AddShoppingCartResponse response = addShoppingCartService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "shopping_cart/addShoppingCart";
        } else {
            return "redirect:/";
        }
    }

}