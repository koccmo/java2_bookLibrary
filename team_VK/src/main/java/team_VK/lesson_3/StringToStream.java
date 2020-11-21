package team_VK.lesson_3;

import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class StringToStream {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter");
        String str = scanner.nextLine();

        IntStream streamFromString = str.chars();

        boolean b = streamFromString
                .anyMatch(new IntPredicate() {
                    @Override
                    public boolean test(int value) {
                        return (value <=64) || ((value >=91) && (value <= 96)) || ((value >=123) && (value <=193))
                                || (value >=256);
                    }
                });
        System.out.println(b);

    }


}


//.forEach(s -> System.out.println(s));





