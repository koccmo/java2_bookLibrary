package internet_store.web_ui.controller.cart;

import internet_store.core.domain.Client;
import internet_store.core.operation.Tax;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.DeleteProductFromCartService;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.service.cart.paging.CartPagingService;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.persistence.CartRepository;
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
    CartRepository CartRepository;
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
    @Autowired
    OrderService orderService;

    @GetMapping(value = "cart")
    public String getCart(ModelMap modelMap) {
        long countProductInCart = cartCountService.getCartCount();

        paging.startPaging();

        if (countProductInCart == 0) {
            tax.setTaxAmount(new BigDecimal("0.00"));
            tax.setAmountWithTax(new BigDecimal("0.00"));
        }
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("totalSum", totalSumCartService.calculateTotalSum());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("total", tax.getAmountWithTax(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
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

        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("totalSum", totalSumCartService.calculateTotalSum());
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("total", tax.getAmountWithTax(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
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

        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("totalSum", totalSumCartService.calculateTotalSum());
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("total", tax.getAmountWithTax(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        return "cart/cart";
    }

    @PostMapping(value = "/deleted_from_cart")
    public String deleteProductFromCart(@RequestParam(value = "deletedProduct") Long deletedCartId,
                                        ModelMap modelMap) {

        DeleteProductFromCartRequest request = new DeleteProductFromCartRequest(deletedCartId);
        service.execute(request);
        paging.startPaging();

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("totalSum", totalSumCartService.calculateTotalSum());
        modelMap.addAttribute("currencySymbol", tax.getCurrencySymbol());
        modelMap.addAttribute("taxRate", tax.getTaxRate());
        modelMap.addAttribute("taxAmount", tax.getTaxAmount(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("total", tax.getAmountWithTax(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        orderService.setClient(new Client());
        return "cart/cart";
    }
}