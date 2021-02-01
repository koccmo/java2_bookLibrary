package internet_store.controller;

import internet_store.core.operation.Tax;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.DeleteProductFromCartService;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.service.cart.paging.CartPagingService;
import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartProductsCountService cartCountService;
    @Autowired
    CartPagingService paging;
    @Autowired
    TotalSumCartService totalSumCartService;
    @Autowired
    Tax tax;
    @Autowired
    DeleteProductFromCartService service;
    private BigDecimal totalSumInCart;
    private long cartCount;

    @GetMapping(value = "cart")
    public String getCart(ModelMap modelMap) {
        paging.startPaging();

        updatePage();
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("totalSum", totalSumInCart);
        modelMap.addAttribute("totalSum", totalSumInCart);
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount());
        modelMap.addAttribute("total", tax.getAmountWithTax());
        return "cart/cart";
    }

    @PostMapping(value = "/view_cart_next")
    public String nextPageCartControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }

        updatePage();
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("totalSum", totalSumInCart);
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount());
        modelMap.addAttribute("total", tax.getAmountWithTax());
        return "cart/cart";
    }

    @PostMapping(value = "/view_cart_prev")
    public String prevPageCartControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }

        updatePage();
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("totalSum", totalSumInCart);
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount());
        modelMap.addAttribute("total", tax.getAmountWithTax());
        return "cart/cart";
    }

    @PostMapping(value = "/deleted_from_cart")
    public String deleteProductFromCart(@RequestParam(value = "deletedProduct") String deletedProductTitle,
                                        ModelMap modelMap) {

        DeleteProductFromCartRequest request = new DeleteProductFromCartRequest(0,
                cartRepository, deletedProductTitle);
        service.execute(request);
        paging.startPaging();

        updatePage();
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("totalSum", totalSumInCart);
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount());
        modelMap.addAttribute("total", tax.getAmountWithTax());
        return "cart/cart";
    }

    private void updatePage() {
        cartCount = cartCountService.getCartCount();
        totalSumInCart = totalSumCartService.calculateTotalSum();
        tax.getTaxAmount(totalSumInCart);
        tax.getAmountWithTax(totalSumInCart);
    }
}