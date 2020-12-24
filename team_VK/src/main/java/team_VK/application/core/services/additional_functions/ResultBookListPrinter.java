package team_VK.application.core.services.additional_functions;

import team_VK.application.core.domain.Book;
import team_VK.application.database.DIComponent;

import java.util.List;

@DIComponent
public class ResultBookListPrinter {

    public void execute (List<Book> resultList){
        resultList.forEach(book -> System.out.println(book.toString()));
        System.out.println("----------------------");
        System.out.println();
    }

}
