package team_VK.lesson_1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {

    public static void main(String[] args) {

        Date date = new Date();

        System.out.println(date);
        System.out.println();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(calendar.getTime());
        System.out.println();

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        System.out.println(dateFormat.format(calendar.getTime()));
        System.out.println();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.println(simpleDateFormat.format(calendar.getTime()));

         calendar.add(Calendar.DAY_OF_YEAR, 100);
        Date newDate = calendar.getTime();

        System.out.println(simpleDateFormat.format(newDate));


    }

}
