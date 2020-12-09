package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.validators.SearchProductRequestValidator;
import internet_store.database.product.ProductDatabase;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class SearchProductService {

    @DIDependency private ProductDatabase productDatabase;
    @DIDependency private SearchProductRequestValidator searchProductRequestValidator;

    public SearchProductResponse execute (SearchProductRequest searchProductRequest){
        List<CoreError> errors = searchProductRequestValidator.validate(searchProductRequest);
        if (!errors.isEmpty()){
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchProductRequest);
    }

    private SearchProductResponse provideSearchResultAccordingToRequest(SearchProductRequest searchProductRequest){
        if (isTitleAndDescriptionNotEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription())){
            return searchByTitleAndDescriptionIsProvided(searchProductRequest);
        }
        if (isTitleFilled(searchProductRequest.getTitle())){
            return searchByTitleIsProvided(searchProductRequest);
        }
        return searchByDescriptionIsProvided (searchProductRequest);
    }

    private boolean isTitleAndDescriptionNotEmpty(String title, String description){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty();
    }

    private SearchProductResponse searchByTitleAndDescriptionIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List <Product> products = productDatabase.findAllByTitleAndDescription(searchProductRequest.getTitle(), searchProductRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title: " +
                    searchProductRequest.getTitle() + " and description: " + searchProductRequest.getDescription()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private boolean isTitleFilled(String title){
        return title != null && !title.isEmpty();
    }

    private SearchProductResponse searchByTitleIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products wits title: " +
                    searchProductRequest.getTitle()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByDescriptionIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product>products = productDatabase.findAllByDescription(searchProductRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products wits description: " +
                    searchProductRequest.getDescription()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (ordering.filledBoth()){
            if (ordering.getOrderBy().equals("name")){
                return sortByTitle(products, ordering);
            }else{
                return sortByDescription(products, ordering);
            }
        }else{
            return products;
        }
    }

    private List<Product> sortByTitle(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparing(product -> product.getTitle()))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getTitle().compareTo(o1.getTitle()))
                    .collect(Collectors.toList());
        }
    }

    private List<Product> sortByDescription(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparing(product -> product.getDescription()))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getDescription().compareTo(o1.getDescription()))
                    .collect(Collectors.toList());
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return products.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
