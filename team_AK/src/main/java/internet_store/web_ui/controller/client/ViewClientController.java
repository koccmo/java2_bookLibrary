package internet_store.web_ui.controller.client;

import internet_store.core.service.client.paging.ClientPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewClientController {

    @Autowired
    ClientPagingService paging;
    @Value("${client-records-on-page}")
    private int recordsCountOnPage;

    @GetMapping(value = "view_client")
    public String showClient(ModelMap modelMap) {
        paging.startPaging();

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("clientList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "client/view_client";
    }

    @PostMapping(value = "/view_client_next")
    public String nextPageClientControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("clientList", paging.getListOnePage());
        return "client/view_client";
    }

    @PostMapping(value = "/view_client_prev")
    public String prevPageClientControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("clientList", paging.getListOnePage());
        return "client/view_client";
    }
}