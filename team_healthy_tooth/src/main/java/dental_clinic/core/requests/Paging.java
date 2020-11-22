package dental_clinic.core.requests;

public class Paging {

    private Integer pageNumber;
    private Integer pageSize;

    public Paging(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

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
}
