package lesson_3.user_interface.product_menu.add_product_menu;


import lesson_3.core.domain.Product;

public class AddProduct {
    private final Product product = new Product();

    public Product addProduct() {

        AddProductTitle addProductTitle = new AddProductTitle();
        AddProductDescription addProductDescription = new AddProductDescription();
        AddProductQuantity addProductQuantity = new AddProductQuantity();
        AddProductPrice addProductPrice = new AddProductPrice();

        addProductTitle.showMenuProductTitle();
        product.setTitle(addProductTitle.getUserProductTitleInput());

        addProductDescription.showMenuDescription();
        product.setDescription(addProductDescription.getUserProductDescriptionInput());

        addProductQuantity.showMenuProductQuantity();
        product.setQuantity(addProductQuantity.getUserProductQuantityInput());

        addProductPrice.showMenuProductPrice();
        product.setPrice(addProductPrice.getUserProductPriceInput());

        return product;
    }
}
