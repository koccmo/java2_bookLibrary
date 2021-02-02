package internet_store.core.service.product.paging;

import internet_store.core.domain.Product;
import internet_store.persistence.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTwoPagingService {
    private final int recordsCountOnPage = 6;
    private final int PAGE_OFFSET = 1;
    private final int FIRST_PAGE = 1;
    private final int START_FROM_FIRST_RECORD = 0;
    @Autowired
    ProductRepository productRepository;
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
    @Setter
    private List<Product> listOnePage;

    public void startPaging() {
        isFirstPage = true;
        isLastPage = false;
        startRecordOffset = START_FROM_FIRST_RECORD;
        endRecordOffset = recordsCountOnPage;
        currentPage = FIRST_PAGE;
        calculatePagesQuantity();
        listOnePage = productRepository.getLimitsProductsCategoryTwo(recordsCountOnPage, startRecordOffset);
        if ((startRecordOffset + recordsCountOnPage) >= productRepository.getCategoryTwoCount()) {
            isLastPage = true;
        }
    }

    public void nextPage(boolean pagingDirection) {
        if (pagingDirection) {
            nextPage(productRepository.getCategoryTwoCount());
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
        listOnePage = productRepository.getLimitsProductsCategoryTwo(recordsCountOnPage, startRecordOffset);
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
        listOnePage = productRepository.getLimitsProductsCategoryTwo(recordsCountOnPage, startRecordOffset);
    }

    private void calculatePagesQuantity() {
        final int NO_EXTRA_PAGE = 0;

        long categoryTwoCount = productRepository.getCategoryTwoCount();

        if (isAllRecordsCanSetOnePage(categoryTwoCount)) return;

        if ((categoryTwoCount % recordsCountOnPage) == NO_EXTRA_PAGE) {
            pagesQuantity = (int) categoryTwoCount / recordsCountOnPage;
        } else {
            pagesQuantity = ((int) categoryTwoCount / recordsCountOnPage) + PAGE_OFFSET;
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