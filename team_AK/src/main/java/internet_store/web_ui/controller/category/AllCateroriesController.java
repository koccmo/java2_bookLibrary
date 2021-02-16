package internet_store.web_ui.controller.category;

import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import internet_store.core.service.product.paging.AllCategoriesPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AllCateroriesController {
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private CheckStockQuantityService quantityService;
    @Autowired
    private AddToCartService addToCartService;
    @Autowired
    private AllCategoriesPagingService paging;
    @Autowired
    private CartRepository CartRepository;

    @GetMapping(value = "categories")
    public String showAllCategories(ModelMap modelMap) {
        paging.startPaging();
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("error", "");
        refreshData(modelMap);
        return "categories/categories";
    }

    @PostMapping(value = "/pass_from_all_categories")
    // TODO: 24.01.2021  It's only example. Here must be Spring Security
    public String checkPassFromAllCategories(@RequestParam(value = "pass") String password, ModelMap modelMap) {
        if (password.equals("admin")) {
            return "service/service";
        }

        refreshData(modelMap);
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("error", "Login error : Incorrect password");
        return "categories/categories";
    }

    @PostMapping(value = "/buy_product_all_categories")
    public String productBuyFromCategories(@RequestParam(value = "quantity") Long quantity,
                                           @RequestParam(value = "products") String productTitle, ModelMap modelMap) {

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

        refreshData(modelMap);
        modelMap.addAttribute("info", "");
        return "categories/categories";
    }

    @PostMapping(value = "/categories_next")
    public String nextPageCategoriesControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }

        refreshData(modelMap);
        modelMap.addAttribute("error", "");
        return "categories/categories";
    }

    @PostMapping(value = "/categories_prev")
    public String prevPageCategoriesControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }

        refreshData(modelMap);
        modelMap.addAttribute("error", "");
        return "categories/categories";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("products", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
    }
}