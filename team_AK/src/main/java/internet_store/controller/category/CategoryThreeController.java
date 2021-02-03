package internet_store.controller.category;

import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import internet_store.core.service.product.paging.CategoryThreePagingService;
import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryThreeController {
    @Autowired
    CartProductsCountService cartCountService;
    @Autowired
    CheckStockQuantityService quantityService;
    @Autowired
    AddToCartService addToCartService;
    @Autowired
    CategoryThreePagingService paging;
    @Autowired
    CartRepository CartRepository;
    private long cartCount;

    @GetMapping(value = "category_3")
    public String showCategoryThree(ModelMap modelMap) {
        paging.startPaging();

        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_three";
    }

    @PostMapping(value = "/pass_from_category_three")
    // TODO: 24.01.2021  It's only example. Here must be Spring Security
    public String checkPassFromCategoryThree(@RequestParam(value = "pass") String password, ModelMap modelMap) {
        if (password.equals("admin")) {
            return "service/service";
        }
        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "Login error : Incorrect password");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_two";
    }

    @PostMapping(value = "/buy_product_category_three")
    public String productBuyFromCategoryThree(@RequestParam(value = "quantity") Long quantity,
                                              @RequestParam(value = "productTitle") String title, ModelMap modelMap) {

        CheckStockQuantityRequest request = new CheckStockQuantityRequest(quantity, title);
        CheckStockQuantityResponse response = quantityService.execute(request);

        if (response.hasErrors()) {
            String errorMessage = response.getErrors().get(0).getMessage();
            modelMap.addAttribute("error", errorMessage);
        } else {
            AddProductToCartRequest addRequest = new AddProductToCartRequest(0, quantity, CartRepository, title);
            addToCartService.execute(addRequest);
            modelMap.addAttribute("error", "");
        }
        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_three";
    }

    @PostMapping(value = "/categoryThree_next")
    public String nextPageCategoryThreeControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }
        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_three";
    }

    @PostMapping(value = "/categoryThree_prev")
    public String prevPageCategoryThreeControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }
        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_three";
    }

    private void updateCartCount() {
        cartCount = cartCountService.getCartCount();
    }
}