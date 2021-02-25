package adventure_time.ui.guides;

import adventure_time.core.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;
import adventure_time.ui.UIAction;

public class StartUpGuideUIAction implements UIAction {

    private DatabaseGuides databaseGuides;

    public StartUpGuideUIAction(DatabaseGuides databaseGuides) {
        this.databaseGuides = databaseGuides;
    }

    @Override
    public void execute() {
        Guides guides = new Guides("Alex","alex@guides.com","12345678");
        databaseGuides.add(guides);

        guides = new Guides("Alex2","alex2@guides.com","23456789");
        databaseGuides.add(guides);

        guides = new Guides("Alexandr","alexandr@guides.com","345678900");
        databaseGuides.add(guides);

        guides = new Guides("Boris","boris@guides.com","456789001");
        databaseGuides.add(guides);

        guides = new Guides("Bogdan","bogdan@guides.com","56789002");
        databaseGuides.add(guides);

        guides = new Guides("Susanin","susanin@guides.com","666000666");
        databaseGuides.add(guides);
    }
}