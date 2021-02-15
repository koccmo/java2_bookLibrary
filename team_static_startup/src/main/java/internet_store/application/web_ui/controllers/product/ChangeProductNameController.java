package internet_store.application.web_ui.controllers.product;

import internet_store.application.core.requests.product.ChangeProductNameRequest;
import internet_store.application.core.responses.product.ChangeProductNameResponse;
import internet_store.application.core.services.product.ChangeProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeProductNameController {

    @Autowired
    ChangeProductNameService changeProductNameService;

    @GetMapping(value = "product/changeProductName")
    public String showChangeProductNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ChangeProductNameRequest());
        return "product/changeProductName";
    }

    @PostMapping(value = "product/changeProductName")
    public String processChangeProductName(@ModelAttribute(value = "request") ChangeProductNameRequest request,
                                           ModelMap modelMap) {
        ChangeProductNameResponse response = changeProductNameService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("products", response.isNameChanged());
        }
        return "product/changeProductName";
    }
}
