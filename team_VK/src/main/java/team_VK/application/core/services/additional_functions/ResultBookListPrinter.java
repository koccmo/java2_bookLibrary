package team_VK.application.core.services.additional_functions;

import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;

import java.util.List;

@Component
public class ResultBookListPrinter {

    public void execute (List<Book> resultList){
        resultList.forEach(book -> System.out.println(book.toString()));
        System.out.println("----------------------");
        System.out.println();
    }

}
