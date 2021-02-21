package internet_store.core.requests;

public class Paging {

    public Integer pageNumber;
    public Integer pageSize;

    public Paging(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Paging () { }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public boolean isFilledBoth(){
        return pageNumber != null && pageSize != null;
    }

    public boolean isEmptyBoth(){
        return pageNumber == null && pageNumber == null;
    }

    public boolean isFilledOne(){
        return (pageNumber == null && pageSize != null ) ||
                (pageNumber != null && pageSize == null);
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
