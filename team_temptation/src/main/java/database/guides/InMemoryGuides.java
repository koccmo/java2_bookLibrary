package database.guides;

import domain.Guides;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGuides {

    private long idCounter = 1L;
    private final List<Guides> guides = new ArrayList<>();

    public boolean add(Guides guides) {
        if (!this.guides.isEmpty()) {
            for (Guides item : this.guides) {
                if (item.getguideName().equals(guides.getguideName())) return false;
            }
        }
        guides.setguideID(idCounter);
        this.guides.add(guides);
        idCounter++;
        return true;
    }

    public boolean remove (String guideName) {
        return getGuidesList().removeIf(items -> items.getguideName().equals(guideName));
    }

    public List<Guides> getGuidesList() {
        return guides;
    }
}