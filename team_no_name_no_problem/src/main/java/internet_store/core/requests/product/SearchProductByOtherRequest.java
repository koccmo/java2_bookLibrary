package internet_store.core.requests.product;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;

public class SearchProductByOtherRequest {

    private String title;
    private String description;
    private Integer startPrice;
    private Integer endPrice;
    public Ordering ordering;
    public Paging paging;

    public SearchProductByOtherRequest(){}

    public SearchProductByOtherRequest(String title, String description, Integer startPrice,
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
