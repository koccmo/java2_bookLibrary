package estore.ui;

import estore.core.requests.GetAllProductCategoriesRequest;
import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.GetAllProductCategoriesResponse;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.service.GetAllProductCategoriesService;
import estore.core.service.PrintListService;
import estore.core.service.SearchProductByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchProductByCategoryUI implements UIAction {

    private SearchProductByCategoryService searchProductByCategoryService;
    @Autowired
    private GetAllProductCategoriesService getAllProductCategoriesService;

    public SearchProductByCategoryUI(SearchProductByCategoryService searchProductByCategoryService) {
        this.searchProductByCategoryService = searchProductByCategoryService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        GetAllProductCategoriesRequest getAllProductCategoriesRequest = new GetAllProductCategoriesRequest();
        GetAllProductCategoriesResponse getAllProductCategoriesResponse = getAllProductCategoriesService.execute(getAllProductCategoriesRequest);

        for (var category: getAllProductCategoriesResponse.getCategories()) {
            System.out.print(category.getCategory() + "   ");
        }
        System.out.println();
        System.out.println("Enter category of the product to search: ");
        String categoryToSearch = sc.nextLine();

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

        SearchProductByCategoryRequest request = new SearchProductByCategoryRequest(categoryToSearch, ordering, paging);
        SearchProductByCategoryResponse response = searchProductByCategoryService.execute(request);

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
