package adventure_time.core.services.guides;

import adventure_time.core.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;

import java.util.List;

public class DisplayGuideListService {
    private final DatabaseGuides databaseGuides;

    public DisplayGuideListService(DatabaseGuides databaseGuides) {
        this.databaseGuides = databaseGuides;
    }

    public List<Guides> getGuidesList() {
        return databaseGuides.getGuidesList();
    }

}
