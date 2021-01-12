package adventure_time.core.services.guides;

import database.guides.DatabaseGuides;
import domain.Guides;

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
