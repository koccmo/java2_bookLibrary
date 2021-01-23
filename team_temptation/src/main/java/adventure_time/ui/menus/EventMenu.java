package adventure_time.ui.menus;

public class EventMenu implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("EVENTS:");
        System.out.println("1. Add new event"); // return to mainMenu
        System.out.println("2. Delete an event");
        System.out.println("3. Search events");
        System.out.println("4. Update an event");
        System.out.println("5. Show all events");
        System.out.println("6. Return to Main menu");
        System.out.println("7. Start with the defined DB");
        System.out.println();
    }
}
