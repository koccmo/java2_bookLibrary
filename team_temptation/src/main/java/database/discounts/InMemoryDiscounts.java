package database.discounts;

import core.requests.events.SearchEventRequest;
import database.events.EventDatabase;
import domain.Discounts;
import domain.Events;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiscounts implements DiscountDatabase {

    private long idCounter = 1L;
    private List<Events> discounts = new ArrayList<>();


    @Override
    public boolean add(Discounts discount) {
        return false;
    }

    @Override
    public boolean remove (String eventName) {
        return true;
        //getEventsList().removeIf(items -> items.getEventName().equals(eventName));
    }

    @Override
    public List<Discounts> getDiscountsList() {
        return null;
    }

    @Override
    public List<Discounts> searchDiscounts() {
        return null;
    }

}
