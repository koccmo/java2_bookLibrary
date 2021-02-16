package internet_store.web_ui.controllers;


import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeProductController {

    @Autowired
    private ChangeProductService changeProductService;

    @GetMapping(value = "/changeProduct")
    public String showChangeProductPage(ModelMap modelMap){
        modelMap.addAttribute("request", new ChangeProductRequest());
        return "changeProduct";
    }

    @PostMapping("/changeProduct")
    public String processChangeProductRequest(@ModelAttribute(value = "request") ChangeProductRequest request, ModelMap modelMap){
        ChangeProductResponse response = changeProductService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
            return "changeProduct";
        } else {
            return "index";
        }
    }
}
