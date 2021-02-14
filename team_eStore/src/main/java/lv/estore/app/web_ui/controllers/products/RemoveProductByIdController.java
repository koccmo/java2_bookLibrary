package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.services.products.RemoveProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveProductByIdController {

    @Autowired
    private RemoveProductByIdService service;

    @GetMapping(value = "products/removeProductById")
    public String showRemoveProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ProductIdRequest());
        return "products/removeProductById";
    }

    @PostMapping("products/removeProductById")
    public String processRemoveProductByIdRequest(@ModelAttribute(value = "request") ProductIdRequest request,
                                                  ModelMap modelMap) {
        RemoveProductResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "products/removeProductById";
        } else {
            return "redirect:/products/";
        }
    }
}
