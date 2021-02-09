package internet_store.application.web_ui.controllers;

import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.DeleteByProductIdRequest;
import internet_store.application.core.responses.product.DeleteByProductIdResponse;
import internet_store.application.core.services.product.DeleteByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteProductController {

    @Autowired
    private DeleteByProductIdService deleteByProductIdService;

    @GetMapping("/deleteProduct")
    public String showAddProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteByProductIdRequest());
        return "deleteProduct";
    }

    @PostMapping(value = "/deleteProduct")
    public String deleteProduct(@ModelAttribute(value = "request") DeleteByProductIdRequest request, ModelMap modelMap) {
        DeleteByProductIdResponse response = deleteByProductIdService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteProduct";
        } else {
            return "redirect:/";
        }
    }

}
