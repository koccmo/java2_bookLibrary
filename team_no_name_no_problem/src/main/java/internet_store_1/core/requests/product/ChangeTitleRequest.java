package internet_store_1.core.requests.product;

public class ChangeTitleRequest {

    private long id;
    private String title;

    public ChangeTitleRequest(long id, String title){
        this.id = id;
        this.title = title;
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

}
