package team_VK.application.core.requests;

public class GetBookListRequest {

    public String userName;
    public boolean havesPermit;
    private int sortingCriteria;
    private  int booksPerPage;

    public GetBookListRequest(int sortingCriteria, int booksPerPage) {
        this.sortingCriteria = sortingCriteria;
        this.booksPerPage = booksPerPage;
    }

    public GetBookListRequest() {
    }

    public int getSortingCriteria() {
        return sortingCriteria;
    }

    public int getBooksPerPage() {
        return booksPerPage;
    }
}
