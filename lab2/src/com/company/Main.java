package com.company;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void problema1() {
        int[] arr = new int[20];
        double nr = 0, sum = 0;
        while(nr < 20) {
            double a = in.nextInt();
            if(a != -1) {
                sum += a;
                nr++;
            }
            else {
                break;
            }
        }
        System.out.println(sum/nr);
    }

    public static void problema2() {
        System.out.println(new Person("John", "Doe", "24", "student", 1123444L));
        System.out.println(new Person("Jane", "Roe", "56", "teacher", 2233444L));
    }

    public static void problema3() {
        System.out.println(new Room("12A", "normal", 3));
        System.out.println(new Room("12B", "tech", 7));
    }

    public static void problema4() {
        Room room1 = new Room("12A", "normal", 3);
        Person teacher1 = new Person("Jane", "Roe", "56", "teacher", 2233444L);
        System.out.println(new Subject(room1, teacher1, 25));

        Room room2 = new Room("12B", "normal", 7);
        Person teacher2 = new Person("Joe", "Reid", "45", "teacher", 2266444L);
        System.out.println(new Subject(room2, teacher2, 30));
    }

    public static void main(String[] args) {
	    // write your code here
        //problema1();
        //problema2();
        //problema3();
        problema4();
    }
}
