ALTER TABLE `shopping_cart`
ADD FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

ALTER TABLE `shopping_cart`
ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);