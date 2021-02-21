package internet_store.web_ui.controllers;


import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.product.DeleteProductByIdResponse;
import internet_store.core.services.product.DeleteProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteProductByIdController {

    @Autowired
    private DeleteProductByIdService deleteByIdService;

    @GetMapping(value = "/deleteProductById")
    public String showDeleteProductByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteProductByIdRequest());
        return "deleteProductById";
    }

    @PostMapping("/deleteProductById")
    public String processDeleteProductByIdRequest(@ModelAttribute(value = "request") DeleteProductByIdRequest deleteProductRequest, ModelMap modelMap) {
        DeleteProductByIdResponse deleteProductResponse = deleteByIdService.execute(deleteProductRequest);
        if (deleteProductResponse.hasErrors()) {
            modelMap.addAttribute("errors", deleteProductResponse.getErrors());
            return "/deleteProductById";
        } else {
            return "index";
        }
    }
}
