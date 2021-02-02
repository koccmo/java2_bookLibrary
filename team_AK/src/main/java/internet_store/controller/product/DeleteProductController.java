package internet_store.controller.product;

import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.service.product.DeleteProductService;
import internet_store.core.service.product.SearchProductService;
import internet_store.core.domain.Product;
import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteProductController {
    @Autowired
    SearchProductService service;
    @Autowired
    DeleteProductService deletedProductService;
    @Autowired
    ProductRepository productRepository;

    private Product deletedProduct;

    @GetMapping(value = "delete_product")
    public String deleteProduct(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        return "product/delete_product";
    }

    @PostMapping(value = "/search_deleting_product")
    public String productForDeleting(@RequestParam(value = "search") String title, ModelMap modelMap) {
        SearchProductByTitleRequest request = new SearchProductByTitleRequest(title);
        SearchProductByTitleResponse response = service.execute(request);

        deletedProduct = response.getProduct();

        if (deletedProduct != null) {
            modelMap.addAttribute("error", "");
            modelMap.addAttribute("product", deletedProduct);
        } else {
            modelMap.addAttribute("error", "Search command error : Product not find in database");
        }
        return "product/delete_product";
    }

    @PostMapping(value = "/delete_product")
    public String deleteProductAction(ModelMap modelMap) {
        if (deletedProduct == null) {
            modelMap.addAttribute("error", "Delete command error : Product not exist in database");
        } else {
            modelMap.addAttribute("error", "Product deleted successfully");
            deletedProductService.execute(new DeleteProductRequest(productRepository, deletedProduct, 0));
            deletedProduct = null;
        }
        return "product/delete_product";
    }
}