package lesson_2.service;

import lesson_2.database.InnerDatabase;

public class ProductReportService {
    private final InnerDatabase database;

    public ProductReportService(InnerDatabase database) {
        this.database = database;
    }

    public void execute() {
        database.showReport();
    }
}
