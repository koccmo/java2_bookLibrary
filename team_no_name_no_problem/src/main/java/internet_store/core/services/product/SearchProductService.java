package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.validators.SearchProductRequestValidator;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component public class SearchProductService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private SearchProductRequestValidator searchProductRequestValidator;

    public SearchProductResponse execute(SearchProductRequest searchProductRequest) {
        List<CoreError> errors = searchProductRequestValidator.validate(searchProductRequest);
        if (!errors.isEmpty()) {
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchProductRequest);
    }

    private SearchProductResponse provideSearchResultAccordingToRequest(SearchProductRequest searchProductRequest) {

        if (isTitleAndDescriptionAndPriceNotEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                searchProductRequest.getStartPrice(), searchProductRequest.getEndPrice())) {
            return searchByTitleAndDescriptionAndPriceIsProvided(searchProductRequest);
        }
        if (isTitleAndDescriptionFilled(searchProductRequest.getTitle(),searchProductRequest.getDescription())) {
            return searchByTitleAndDescriptionIsProvided(searchProductRequest);
        }
        if (isTitleFilled(searchProductRequest.getTitle())) {
            return searchByTitleIsProvided(searchProductRequest);
        }
        if (isDescriptionFilled(searchProductRequest.getDescription())) {
            return searchByDescriptionIsProvided(searchProductRequest);
        }
        return searchByTitleAndDescriptionAndPriceIsProvided(searchProductRequest);
    }

    private boolean isTitleAndDescriptionAndPriceNotEmpty(String title, String description,
                                                          Integer startPrice, Integer endPrice){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty() &&
                startPrice != null && endPrice != null;
    }

    private SearchProductResponse searchByTitleAndDescriptionAndPriceIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List <Product> products = productDatabase.findAllByTitleAndDescription(searchProductRequest.getTitle(), searchProductRequest.getDescription());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title: " +
                    searchProductRequest.getTitle() + ", description: " + searchProductRequest.getDescription() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private boolean isTitleFilled(String title){
        return title != null && !title.isEmpty();
    }

    private boolean isDescriptionFilled(String description) {
        return description != null && !description.isEmpty();
    }

    private boolean isPriceRangeFilled(Integer startPrice, Integer endPrice) {
        return startPrice != null && endPrice != null;
    }

    private boolean isTitleAndDescriptionFilled(String title, String description){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty();
    }

    private boolean isTitleFilledAndPriceRangeNotEmpty(String title, Integer startPrice, Integer endPrice) {
        return title != null && !title.isEmpty() && startPrice != null && endPrice != null;
    }

    private boolean isDescriptionFilledPriceRangeNotEmpty(String description, Integer startPrice, Integer endPrice) {
        return description != null && !description.isEmpty() && startPrice != null && endPrice != null;
    }

    private SearchProductResponse searchByTitleIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " +
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
            errors.add(new CoreError("database", "Database doesn't contain products with description: " +
                    searchProductRequest.getDescription()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByPriceRangeIsProvided(SearchProductRequest searchProductRequest) {
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByPriceRange(searchProductRequest.getStartPrice(),
                searchProductRequest.getEndPrice());
        if (products.isEmpty()) {
            errors.add (new CoreError("database","Database doesn't contain products with price" +
                                            " range starting from: " + searchProductRequest.getStartPrice() +
                                            " end ending with " + searchProductRequest.getEndPrice()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByTitleAndDescriptionIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " +
                    searchProductRequest.getTitle() +  ", description: " + searchProductRequest.getDescription()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByTitleAndPriceRangeIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " + searchProductRequest.getTitle() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByDescriptionAndPriceRangeIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitle(searchProductRequest.getTitle());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with description: " + searchProductRequest.getDescription() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        products = order(products, searchProductRequest.getOrdering());
        products = paging(products, searchProductRequest.getPaging());
        return new SearchProductResponse(products);
    }

    private SearchProductResponse searchByTitleAndDescriptionAndPriceRangeIsProvided(SearchProductRequest searchProductRequest){
        List <CoreError>errors = new ArrayList<>();
        List<Product> products = productDatabase.findAllByTitleAndDescriptionAndPriceRange(searchProductRequest.getTitle(),
                searchProductRequest.getDescription(),searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice());
        if (products.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products with title: " + searchProductRequest.getDescription() +
                    ", price range: from " + searchProductRequest.getStartPrice() + " till " + searchProductRequest.getEndPrice()));
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
            }else if(ordering.getOrderBy().equals("description")){
                return sortByDescription(products, ordering);
            }else{
                return sortByPrice(products,ordering);
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

    private List<Product> sortByPrice(List<Product> products, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return products.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice))
                    .collect(Collectors.toList());
        }else{
            return products.stream()
                    .sorted((o1,o2) -> o2.getPrice() - o1.getPrice())
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
