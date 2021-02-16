package internet_store.web_ui.controllers;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {

    @Autowired private AddProductService addProductService;

    @GetMapping(value = "/addProduct")
    public String showAddProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddProductRequest());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String processAddProductRequest(@ModelAttribute(value = "request") AddProductRequest request, ModelMap modelMap) {
        AddProductResponse response = addProductService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/addProduct";
        } else {
        return "index";
    }
    }
}
