package electronic_library.core.requests.reader;

import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;

public class FindReadersRequest {

    public String readerFirstName;
    public String readerLastName;
    public String readerPersonalCode;
    private Ordering ordering;
    private Paging paging;

    public FindReadersRequest(String readerFirstName, String readerLastName, String readerPersonalCode) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
    }

    public FindReadersRequest(String readerFirstName, String readerLastName, String readerPersonalCode, Ordering ordering, Paging paging) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.ordering = ordering;
        this.paging = paging;
    }

    public FindReadersRequest(String readerFirstName, String readerLastName, String readerPersonalCode, Ordering ordering) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.ordering = ordering;
    }

    public FindReadersRequest(String readerFirstName, String readerLastName, String readerPersonalCode, Paging paging) {
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerPersonalCode = readerPersonalCode;
        this.paging = paging;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

    public String getReaderPersonalCode() {
        return readerPersonalCode;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isReaderFirstNameProvided() {
        return this.readerFirstName != null && !this.readerFirstName.isEmpty();
    }

    public boolean isReaderLastNameProvided() {
        return this.readerLastName != null && !this.readerLastName.isEmpty();
    }

    public boolean isReaderPersonalCodeProvided() {
        return this.readerPersonalCode != null && !this.readerPersonalCode.isEmpty();
    }

}
