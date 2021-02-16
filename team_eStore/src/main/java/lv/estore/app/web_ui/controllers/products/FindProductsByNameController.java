package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.FindProductsByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindProductsByNameController {

    @Autowired
    private FindProductsByNameService service;

    @GetMapping(value = "products/findProductsByName")
    public String showFindProductsByNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ProductNameRequest());
        return "products/findProductsByName";
    }

    @PostMapping("products/findProductsByName")
    public String processFindProductsByNameRequest(@ModelAttribute(value = "request") ProductNameRequest request,
                                           ModelMap modelMap) {
        GetProductsResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("products", response.getProducts());
        }
        return "products/findProductsByName";
    }
}
