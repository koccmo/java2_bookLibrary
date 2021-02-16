package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.UpdateProductByIdRequest;
import lv.estore.app.core.responses.products.UpdateProductResponse;
import lv.estore.app.core.services.products.UpdateProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateProductController {

    @Autowired
    private UpdateProductByIdService service;

    @GetMapping(value = "products/updateProduct")
    public String showUpdateProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateProductByIdRequest());
        return "products/updateProduct";
    }

    @PostMapping("products/updateProduct")
    public String processUpdateProductRequest(@ModelAttribute(value = "request") UpdateProductByIdRequest request, ModelMap modelMap) {
        UpdateProductResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "products/updateProduct";
        } else {
            return "redirect:/products/";
        }
    }
}
