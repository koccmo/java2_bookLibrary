package adventure_time.ui.menus;

public class CustomerMenu implements SubjectMenu {

    @Override
    public void show() {
        System.out.println("CUSTOMERS:");
        System.out.println("1. Return to Main menu");
        System.out.println("2. Add new customer");
        System.out.println("3. Delete a customer");
        System.out.println("4. Search a customer by ID/by login (email)");
        System.out.println("5. Show a list of all active/inactive customers");
        System.out.println("6. Update customer's data");
        System.out.println();
    }
}
