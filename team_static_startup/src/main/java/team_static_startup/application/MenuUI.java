package homeLab;

import java.util.Scanner;

class MenuUI {

    public int menu () {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.println("ENTER NUMBER TO:");
        System.out.println("1. add product");
        System.out.println("2. remove product");
        System.out.println("3. print products list to console");
        System.out.println("4. end program");

        return sc.nextInt();
    }

}
