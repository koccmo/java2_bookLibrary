package internet_store.core.requests.product;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;

public class SearchProductRequest {

    private String title;
    private String description;
    private Ordering ordering;
    private Paging paging;

    public SearchProductRequest(String title, String description, Ordering ordering, Paging paging){
        this.title = title;
        this.description = description;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Ordering getOrdering(){
        return ordering;
    }

    public Paging getPaging(){
        return paging;
    }
}
