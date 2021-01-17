package java2.application_target_list.core.validators.board;

public class AddRecordValidatorTest {

//    private AddRecordValidator addRecordValidator;
//    private UserDatabase userDatabase;
//    private TargetDatabase targetDatabase;
//
//    @Before
//    public void setup() {
//        addRecordValidator = new AddRecordValidator();
//        userDatabase = new UserListImpl();
//        targetDatabase = new TargetListImpl();
//        userDatabase.addUser(new User("name", "surname"));
//        targetDatabase.addTarget(new Target("name", "description", 1));
//    }
//
//    @Test
//    public void testValidate_validRequest() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, 1L);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertTrue(actualErrors.isEmpty());
//    }
//
//
//    @Test
//    public void testValidate_invalidRequest_v3() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(-1L, 1L);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 1);
//        Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
//    }
//
//    @Test
//    public void testValidate_invalidRequest_v4() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, -1L);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 1);
//        Assert.assertEquals(actualErrors.get(0).getField(), "User ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
//    }
//
//    @Test
//    public void testValidate_invalidRequest_v5() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(-1L, -1L);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 2);
//        Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
//        Assert.assertEquals(actualErrors.get(1).getField(), "User ID");
//        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be negative!");
//    }

    //    @Test
//    public void testValidate_invalidRequest_v1() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(null, 1L);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 1);
//        Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
//    }

//    @Test
//    public void testValidate_invalidRequest_v2() {
//        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, null);
//        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 1);
//        Assert.assertEquals(actualErrors.get(0).getField(), "User ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
//    }
}