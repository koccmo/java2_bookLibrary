package adventure_time.database.guides;

import adventure_time.core.domain.Guides;

import java.util.List;

public interface DatabaseGuides {

    boolean add(Guides guide);

    boolean remove(String guideName);

    List<Guides> getGuidesList();

}