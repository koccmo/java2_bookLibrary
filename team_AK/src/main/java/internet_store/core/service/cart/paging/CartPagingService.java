package internet_store.core.service.cart.paging;

import internet_store.core.domain.Cart;
import internet_store.persistence.CartRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartPagingService {
    private final int PAGE_OFFSET = 1;
    private final int FIRST_PAGE = 1;
    private final int START_FROM_FIRST_RECORD = 0;
    @Autowired
    CartRepository cartRepository;
    @Getter
    @Setter
    private int recordsCountOnPage;
    private int startRecordOffset;
    private int endRecordOffset;
    @Getter
    private int pagesQuantity;
    @Getter
    private int currentPage;
    @Getter
    private boolean isFirstPage;
    @Getter
    private boolean isLastPage;
    @Getter
    private List<Cart> listOnePage;

    public void startPaging() {
        final int LIMIT_RECORDS_ON_ONE_PAGE = 4;
        isFirstPage = true;
        isLastPage = false;
        startRecordOffset = START_FROM_FIRST_RECORD;
        endRecordOffset = recordsCountOnPage;
        recordsCountOnPage = LIMIT_RECORDS_ON_ONE_PAGE;
        currentPage = FIRST_PAGE;
        calculatePagesQuantity();
        listOnePage = cartRepository.getLimitsCartRecords(recordsCountOnPage, startRecordOffset);
        if ((startRecordOffset + recordsCountOnPage) >= cartRepository.count()) {
            isLastPage = true;
        }
    }

    public void nextPage(boolean pagingDirection) {
        if (pagingDirection) {
            nextPage(cartRepository.count());
        } else {
            prevPage();
        }
    }

    private void nextPage(long allRecordsNumber) {
        if (currentPage + PAGE_OFFSET == pagesQuantity) {
            currentPage++;
            startRecordOffset += recordsCountOnPage;
            isLastPage = true;
            isFirstPage = false;
        } else {
            currentPage++;
            startRecordOffset += recordsCountOnPage;
            endRecordOffset += recordsCountOnPage;
            isLastPage = false;
            isFirstPage = false;
        }
        listOnePage = cartRepository.getLimitsCartRecords(recordsCountOnPage, startRecordOffset);
    }

    private void prevPage() {
        if (currentPage - PAGE_OFFSET == FIRST_PAGE) {
            currentPage--;
            startRecordOffset = START_FROM_FIRST_RECORD;
            endRecordOffset = recordsCountOnPage;
            isFirstPage = true;
            isLastPage = false;
        } else {
            currentPage--;
            startRecordOffset -= recordsCountOnPage;
            isFirstPage = false;
            isLastPage = false;
        }
        listOnePage = cartRepository.getLimitsCartRecords(recordsCountOnPage, startRecordOffset);
    }

    private void calculatePagesQuantity() {
        final int NO_EXTRA_PAGE = 0;

        long searchResultCount = cartRepository.count();

        if (isAllRecordsCanSetOnePage(searchResultCount)) return;

        if ((searchResultCount % recordsCountOnPage) == NO_EXTRA_PAGE) {
            pagesQuantity = (int) searchResultCount / recordsCountOnPage;
        } else {
            pagesQuantity = ((int) searchResultCount / recordsCountOnPage) + PAGE_OFFSET;
        }
    }

    private boolean isAllRecordsCanSetOnePage(long searchResultCount) {
        final int ONLY_ONE_PAGE = 1;
        if (searchResultCount < recordsCountOnPage) {
            pagesQuantity = ONLY_ONE_PAGE;
            return true;
        }
        return false;
    }
}