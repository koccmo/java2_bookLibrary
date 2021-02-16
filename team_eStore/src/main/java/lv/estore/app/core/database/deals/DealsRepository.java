package lv.estore.app.core.database.deals;

import lv.estore.app.core.domain.Deal;

import java.util.List;

public interface DealsRepository {
    Long addDeal(Deal deal);
    Deal findDealById(Long id);
    List<Deal> findDealsByUserId(Long id);
    List<Deal> findDealsByProductId(Long id);
    List<Deal> getAllDeals();
    boolean removeDealByID(Long id);
    boolean updateDealById(Long id, Long userId, Long productId, String status);
}
