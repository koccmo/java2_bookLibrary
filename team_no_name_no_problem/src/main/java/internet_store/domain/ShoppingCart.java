package internet_store.domain;

public class ShoppingCart {

    private String itemName;
    private int itemCost;
    private int quantity;

    public ShoppingCart(String itemName, int itemCost, int quantity) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.quantity = quantity;
        // что еще можно сюда добавить?
    }


}
