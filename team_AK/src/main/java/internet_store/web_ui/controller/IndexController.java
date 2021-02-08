package internet_store.web_ui.controller;

import internet_store.core.domain.Product;
import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import internet_store.core.service.product.ProductRangeService;
import internet_store.core.service.product.RandomProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    ProductRangeService rangeService;
    @Autowired
    CheckStockQuantityService quantityService;
    @Autowired
    CartProductsCountService cartCountService;
    @Autowired
    AddToCartService addToCartService;
    @Autowired
    RandomProductListService randomService;
    @Autowired
    CartRepository CartRepository;

    private List<Product> startPageProducts;
    private long cartCount;

    @GetMapping(value = "/")
    public String index(ModelMap modelMap) {
        updatePage();

        Path resourceDirectory = Paths.get("resources");

        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("products", startPageProducts);
        return "index";
    }

    @PostMapping(value = "/buy_product")
    public String productBuy(@RequestParam(value = "quantity") Long quantity,
                             @RequestParam(value = "buy_product") String productTitle, ModelMap modelMap) {

        CheckStockQuantityRequest request = new CheckStockQuantityRequest(quantity, productTitle);
        CheckStockQuantityResponse response = quantityService.execute(request);

        if (response.hasErrors()) {
            String errorMessage = response.getErrors().get(0).getMessage();
            modelMap.addAttribute("error", errorMessage);
        } else {
            AddProductToCartRequest addRequest = new AddProductToCartRequest(quantity, productTitle);
            addToCartService.execute(addRequest);
            modelMap.addAttribute("error", "");
        }
        updatePage();
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("products", startPageProducts);
        return "index";
    }

    @PostMapping(value = "/pass")
    // TODO: 24.01.2021  It's only example. Here must be Spring Security
    public String checkPass(@RequestParam(value = "pass") String password, ModelMap modelMap) {
        if (password.equals("admin")) {
            return "service/service";
        }
        updatePage();
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "Login error : Incorrect password");
        modelMap.addAttribute("products", startPageProducts);
        return "index";
    }

    private void updatePage() {
        cartCount = cartCountService.getCartCount();

        List<Product> randomList = randomService.createRandomProductsList();
        if (randomList.size() < 6) {
            startPageProducts = rangeService.getProductsRange(6, 0);
        } else {
            startPageProducts = randomList;
        }
    }
}