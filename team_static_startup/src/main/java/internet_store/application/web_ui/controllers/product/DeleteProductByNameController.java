package internet_store.application.web_ui.controllers.product;

import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.product.DeleteByProductNameResponse;
import internet_store.application.core.services.product.DeleteByProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteProductByNameController {

    @Autowired
    private DeleteByProductNameService deleteByProductNameService;

    @GetMapping(value = "/product/deleteProductByName")
    public String showDeleteProductByNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteByProductNameRequest());
        return "/product/deleteProductByName";
    }

    @PostMapping(value = "/product/deleteProductByName")
    public String processDeleteProductByName(@ModelAttribute(value = "request") DeleteByProductNameRequest request,
                                             ModelMap modelMap) {
        DeleteByProductNameResponse response = deleteByProductNameService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/product/deleteProductByName";
        } else {
            modelMap.addAttribute("productRemoved", response.isProductRemoved());
            return "/product/deleteProductByName";
        }
    }

}
