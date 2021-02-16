package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.services.products.RemoveProductByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveProductByNameController {

    @Autowired
    private RemoveProductByNameService service;

    @GetMapping(value = "products/removeProductByName")
    public String showRemoveProductByNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ProductNameRequest());
        return "products/removeProductByName";
    }

    @PostMapping("products/removeProductByName")
    public String processRemoveProductByNameRequest(@ModelAttribute(value = "request") ProductNameRequest request, ModelMap modelMap) {
        RemoveProductResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "products/removeProductByName";
        } else {
            return "redirect:/products/";
        }
    }
}
