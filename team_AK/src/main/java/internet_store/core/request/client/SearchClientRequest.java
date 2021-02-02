package internet_store.core.request.client;

import lombok.Getter;

public class SearchClientRequest {

    @Getter
    private final String searchClient;
    @Getter
    private final String searchBy;

    public SearchClientRequest(String searchClient, String searchBy) {
        this.searchClient = searchClient;
        this.searchBy = searchBy;
    }
}