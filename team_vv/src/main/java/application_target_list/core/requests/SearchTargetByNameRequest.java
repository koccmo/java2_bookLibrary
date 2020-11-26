package application_target_list.core.requests;

public class SearchTargetByNameRequest {

    private final String name;
    private Paging paging;



    public SearchTargetByNameRequest(String name) {
        this.name = name;
    }

    public SearchTargetByNameRequest(String name, Paging paging) {
        this.name = name;
        this.paging = paging;
    }

    public Paging getPaging() {
        return paging;
    }

    public String getName() {
        return name;
    }

}
