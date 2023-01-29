package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.PrintAllBooksTitleRequest;
import bookLibrary.core.response.PrintAllBooksTitleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class PrintAllBookTitleService {
    @Autowired
    private DataBase dataBase;


    public PrintAllBooksTitleResponse execute(PrintAllBooksTitleRequest printAllBooksRequest) {
        List<String> titleList = dataBase.getAllBooksTitle();
        PrintAllBooksTitleResponse printAllBookResponse = new PrintAllBooksTitleResponse(titleList);
        return printAllBookResponse;
    }
}
