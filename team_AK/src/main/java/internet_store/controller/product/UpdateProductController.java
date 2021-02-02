package internet_store.controller.product;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.service.product.SearchProductService;
import internet_store.core.service.product.UpdateProductAddNewChangesService;
import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateProductController {
    @Autowired
    SearchProductService service;
    @Autowired
    UpdateProductAddNewChangesService updateService;
    @Autowired
    ProductRepository productRepository;

    private Product updatedProduct;

    @GetMapping(value = "update_product")
    public String updateProduct(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("disabled", "true");
        modelMap.addAttribute("product", new Product());

        return "product/update_product";
    }

    @PostMapping(value = "/search_update_product")
    public String productForUpdate(@RequestParam(value = "search") String title, ModelMap modelMap) {
        SearchProductByTitleRequest request = new SearchProductByTitleRequest(title);
        SearchProductByTitleResponse response = service.execute(request);

        updatedProduct = response.getProduct();

        if (updatedProduct != null) {
            modelMap.addAttribute("error", "");
            modelMap.addAttribute("disabled", "false");
            modelMap.addAttribute("product", updatedProduct);
        } else {
            modelMap.addAttribute("error", "Search command error : Product not find in database");
            modelMap.addAttribute("disabled", "true");
            modelMap.addAttribute("product", new Product());

        }
        return "product/update_product";
    }

    @PostMapping(value = "/update_product")
    public String productUpdate(@ModelAttribute(value = "product") Product product, ModelMap modelMap) {
        product.setId(updatedProduct.getId());
        updateService.execute(new AddProductRequest(productRepository, product));

        modelMap.addAttribute("error", "Product successful updated to database");
        modelMap.addAttribute("disabled", "true");
        modelMap.addAttribute("product", new Product());
        return "product/update_product";
    }

    @PostMapping(value = "/update_product_back")
    public String backUpdateProductPressed() {
        return "service/service";
    }
}