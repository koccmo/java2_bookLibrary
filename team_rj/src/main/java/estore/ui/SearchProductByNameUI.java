package estore.ui;

import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.PrintListService;
import estore.core.service.SearchProductByNameService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchProductByNameUI implements UIAction {

    private SearchProductByNameService searchProductByNameService;

    public SearchProductByNameUI(SearchProductByNameService searchProductByNameService) {
        this.searchProductByNameService = searchProductByNameService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of product to search: ");
        String productToSearch = sc.nextLine();

        System.out.println("Enter orderBy (name||price): ");
        String orderBy = sc.nextLine();
        System.out.println("Enter orderDirection (ASCENDING/asc||DESCENDING/desc): ");
        String orderDirection = sc.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        String pageNumber = sc.nextLine();
        System.out.println("Enter pageSize: ");
        String pageSize = sc.nextLine();
        Paging paging = new Paging(pageNumber, pageSize);

        SearchProductByNameRequest request = new SearchProductByNameRequest(productToSearch, ordering, paging);
        SearchProductByNameResponse response = searchProductByNameService.execute(request);

        if (response.getProductsFound() == -1) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            PrintListService.printListOfProducts(response.getProducts());
        }
    }

}
