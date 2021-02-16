package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.AddProductRequest;
import lv.estore.app.core.responses.products.AddProductResponse;
import lv.estore.app.core.services.products.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {

    @Autowired
    private AddProductService addProductService;

    @GetMapping(value = "products/addProduct")
    public String showAddProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddProductRequest());
        return "products/addProduct";
    }

    @PostMapping("products/addProduct")
    public String processAddProductRequest(@ModelAttribute(value = "request") AddProductRequest request, ModelMap modelMap) {
        AddProductResponse response = addProductService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "products/addProduct";
        } else {
            return "redirect:/";
        }
    }
}
