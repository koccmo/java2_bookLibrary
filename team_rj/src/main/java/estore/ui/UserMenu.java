package estore.ui;

import java.util.ArrayList;
import java.util.List;

public class UserMenu {

    private List<String> menuItems;

    public UserMenu() {
        initializeMenu();
    }

    private void initializeMenu() {
        menuItems = new ArrayList<>();
        menuItems.add("1 - list of products");
        menuItems.add("2 - find product by name");
        menuItems.add("3 - find product by category");
        menuItems.add("4 - add new product");
        menuItems.add("5 - add new product category");
        menuItems.add("6 - remove product by name");
        menuItems.add("7 - remove product by id");
        menuItems.add("8 - update product");
        menuItems.add("0 - exit");
    }

    public void printUserMenu() {
        for (String item : menuItems) {
            System.out.println(item);
        }
    }

    public int getUserMenuSize() {
        return menuItems.size();
    }

}
