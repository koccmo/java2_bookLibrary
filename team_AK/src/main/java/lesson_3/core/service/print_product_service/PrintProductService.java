package lesson_3.core.service.print_product_service;

import lesson_3.database.product_database.InnerProductDatabase;

public class PrintProductService {
    final InnerProductDatabase productDatabase;

    public PrintProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void print() {
        productDatabase.showReport();
    }
}