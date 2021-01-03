package internet_store.core.requests.product;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;

public class SearchProductRequest {

    private Integer startPrice;
    private Integer endPrice;
    private String title;
    private String description;
    private Ordering ordering;
    private Paging paging;

    public SearchProductRequest(String title, String description, Integer startPrice,
                                Integer endPrice, Ordering ordering, Paging paging){
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Integer getStartPrice() {return startPrice; }

    public Integer getEndPrice() {return endPrice; }

    public Ordering getOrdering(){
        return ordering;
    }

    public Paging getPaging(){
        return paging;
    }
}
