package team_static_startup.application;

class ProductListApplication {

    public static void main(String[] args) {
        MenuUI menuUI = new MenuUI();
        int menuItem = menuUI.menu();

            while (menuItem !=4) {
               switch (menuItem) {
                   case 1: System.out.println("added!");
                        break;
                   case 2: System.out.println("removed!");
                       break;
                   case 3: System.out.println("printed!");
                       break;
               }
                menuItem = menuUI.menu();
            }
    }
}
