package internet_store.core.requests.customer;

public class SearchProductRequest {

    private String title;
    private String description;

    public SearchProductRequest(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }
}
