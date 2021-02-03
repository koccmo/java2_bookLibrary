package internet_store.controller.category;

import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import internet_store.core.service.product.paging.CategoryOnePagingService;
import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryOneController {
    @Autowired
    CartProductsCountService cartCountService;
    @Autowired
    CheckStockQuantityService quantityService;
    @Autowired
    AddToCartService addToCartService;
    @Autowired
    CategoryOnePagingService paging;
    @Autowired
    CartRepository CartRepository;
    private long cartCount;

    @GetMapping(value = "category_1")
    public String showCategoryOne(ModelMap modelMap) {
        paging.startPaging();

        updateCartCount();
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("error", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "categories/category_one";
    }

    @PostMapping(value = "/pass_from_category_one")
    // TODO: 24.01.2021  It's only example. Here must be Spring Security
    public String checkPassFromCategoryOne(@RequestParam(value = "pass") String password, ModelMap modelMap) {
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
        return "categories/category_one";
    }

    @PostMapping(value = "/buy_product_category_one")
    public String productBuyFromCategoryOne(@RequestParam(value = "quantity") Long quantity,
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
        return "categories/category_one";
    }

    @PostMapping(value = "/categoryOne_next")
    public String nextPageCategoryOneControl(ModelMap modelMap) {
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
        return "categories/category_one";
    }

    @PostMapping(value = "/categoryOne_prev")
    public String prevPageCategoryOneControl(ModelMap modelMap) {
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
        return "categories/category_one";
    }

    private void updateCartCount() {
        cartCount = cartCountService.getCartCount();
    }
}