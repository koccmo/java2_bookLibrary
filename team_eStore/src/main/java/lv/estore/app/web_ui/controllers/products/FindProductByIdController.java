package lv.estore.app.web_ui.controllers.products;

import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.FindProductByIdResponse;
import lv.estore.app.core.services.products.FindProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindProductByIdController {

    @Autowired
    private FindProductByIdService service;

    @GetMapping(value = "products/findProductById")
    public String showFindProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ProductIdRequest());
        return "products/findProductById";
    }

    @PostMapping("products/findProductById")
    public String processFindProductByIdRequest(@ModelAttribute(value = "request") ProductIdRequest request,
                                                ModelMap modelMap) {
        FindProductByIdResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("product", response.getProduct());
        }
        return "products/findProductById";
    }
}
