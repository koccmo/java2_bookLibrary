package team_VK.application.core.requests;

public class BookSearchRequest {

    private int searchCriteria;
    private String criteriaValue;

    public BookSearchRequest(int searchCriteria, String criteriaValue) {
        this.searchCriteria = searchCriteria;
        this.criteriaValue = criteriaValue;
    }

    public int getSearchCriteria() {
        return searchCriteria;
    }

    public String getCriteriaValue() {
        return criteriaValue;
    }
}
