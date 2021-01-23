package adventure_time.ui.menus;

public class GuideMenu implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("GUIDES:");
        System.out.println("1. Return to Main menu");
        System.out.println("2. Add new guide");
        System.out.println("3. Delete a guide");
        System.out.println("4. Search a guide by ID/by login (email)");
        System.out.println("5. Show a list of all active/inactive guides");
        System.out.println("6. Update guide's data");
        System.out.println();
    }
}
