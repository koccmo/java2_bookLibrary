package team_static_startup.application;

import java.util.ArrayList;
import java.util.List;

class ProductListApplication {

    List<Product> productList;

    public ProductListApplication() {
        this.productList = new ArrayList();
    }

    public static void main(String[] args) {
        MenuUI menuUI = new MenuUI();
        menuUI.menu();
    }


}
