package estore.database.jdbcrepo;

//@Component
//public class JdbcProductCategoryRepositoryImpl implements ProductCategoryRepository {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<ProductCategory> getDatabase() {
//        return jdbcTemplate.query(
//                "SELECT * FROM productCategory",
//                new ProductCategoryRowMapper()
//        );
//    }
//
//    @Override
//    public boolean addNewCategory(ProductCategory category) {
//        jdbcTemplate.update(
//                "INSERT INTO productCategory (category) " +
//                        "VALUES (?)",
//                category.getCategory());
//        return true;
//    }
//}
