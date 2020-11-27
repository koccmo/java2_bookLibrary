package estore.ui;

import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.service.PrintListService;
import estore.core.service.SearchProductByCategoryService;

import java.util.Scanner;

public class SearchProductByCategoryUI implements UIAction {

    private SearchProductByCategoryService searchProductByCategoryService;

    public SearchProductByCategoryUI(SearchProductByCategoryService searchProductByCategoryService) {
        this.searchProductByCategoryService = searchProductByCategoryService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category of the product to search: ");
        String categoryToSearch = sc.nextLine();

        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest(categoryToSearch);
        SearchProductByCategoryResponse response = searchProductByCategoryService.execute(request);

        if (response.getProductsFound() == -1) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            PrintListService.printListOfProducts(response.getProducts());
        }
    }

}
