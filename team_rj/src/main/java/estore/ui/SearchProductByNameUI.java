package estore.ui;

import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.PrintListService;
import estore.core.service.SearchProductByNameService;

import java.util.Scanner;

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

        SearchProductByNameRequest request = new SearchProductByNameRequest(productToSearch);
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
