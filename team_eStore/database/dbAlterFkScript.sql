ALTER TABLE `deals`
ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `deals`
ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);