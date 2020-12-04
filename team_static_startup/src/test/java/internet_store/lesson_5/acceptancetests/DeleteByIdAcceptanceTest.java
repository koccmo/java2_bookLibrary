package internet_store.lesson_5.acceptancetests;

import internet_store.lesson_5.ApplicationContext;
import internet_store.lesson_5.core.requests.AddProductRequest;
import internet_store.lesson_5.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_5.core.responses.DeleteByProductIdResponse;
import internet_store.lesson_5.core.services.AddProductService;
import internet_store.lesson_5.core.services.DeleteByProductIdService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DeleteByIdAcceptanceTest {

    private ApplicationContext appContext = new ApplicationContext();

  private DeleteByProductIdService getDeleteByProductIdService() {
        return appContext.getBean(DeleteByProductIdService.class);
    }

    private AddProductService getAddProductService() {
      return appContext.getBean(AddProductService.class);
    }

  @Test
    public void shouldDeleteWhenIdIsInDatabase () {
      AddProductRequest addRequest = new AddProductRequest("A1",
              "B1", new BigDecimal("1"));
      getAddProductService().execute(addRequest);
      DeleteByProductIdRequest deleteRequest= new DeleteByProductIdRequest(1L);
      DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
      assertTrue(deleteResponse.isProductRemoved());
      assertFalse(deleteResponse.hasErrors());


  }

  @Test
    public void shouldNotDeleteWhenIdIsNotInDatabase () {
       DeleteByProductIdRequest deleteRequest= new DeleteByProductIdRequest(2L);
        DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
        assertFalse(deleteResponse.isProductRemoved());


    }

    @Test
    public void shouldNotDeleteWhenIdIsNotProvided () {
        DeleteByProductIdRequest deleteRequest= new DeleteByProductIdRequest(null);
        DeleteByProductIdResponse deleteResponse = getDeleteByProductIdService().execute(deleteRequest);
        assertFalse(deleteResponse.isProductRemoved());
        assertTrue(deleteResponse.hasErrors());
        assertEquals(1, deleteResponse.getErrors().size());
        assertEquals("Product ID", deleteResponse.getErrors().get(0).getField());
        assertEquals("Should not be empty.", deleteResponse.getErrors().get(0).getMessage());
  }



}
