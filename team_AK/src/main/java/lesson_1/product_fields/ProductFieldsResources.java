package lesson_1.product_fields;

public enum ProductFieldsResources {
    TITLE("Enter product Title:"),
    DESCRIPTION("Enter product Description:"),
    QUANTITY("Enter product Quantity:"),
    PRICE("Enter product price:");
    private final String text;

    ProductFieldsResources(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
