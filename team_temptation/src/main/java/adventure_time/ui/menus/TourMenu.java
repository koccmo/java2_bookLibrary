package adventure_time.ui.menus;

public class TourMenu implements SubjectMenu {
    @Override
    public void show() {
        System.out.println("TOURS:");
        System.out.println("1. Return to Main menu");
        System.out.println("2. Add new tour");
        System.out.println("3. Delete a tour");
        System.out.println("4. Search a tour by ID");
        System.out.println("5. Show a list of tours by criteria");
        System.out.println("6. Show a list of all active tours");
        System.out.println("7. Show a list of all inactive tours");
        System.out.println("8. Update tour's data");
        System.out.println();
    }
}
