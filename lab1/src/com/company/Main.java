package com.company;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void display_odd() {
        for(int i=1; i<100; i+=2) {
            System.out.println(i);
        }
    }

    public static void compare_2_no() {
        System.out.println("Input data:");
        System.out.println("input first integer: ");
        int a = in.nextInt();
        System.out.println("input second integer: ");
        int b = in.nextInt();
        if(a != b) {
            System.out.println(a + " != " + b);
        }
        else if(a == b) {
            System.out.println(a + " == " + b);
        }
    }

    public static void make_sum(int n) {
        int suma = 0;
        for(int i=3; i<=n; i++) {
            if(i%3==0 || i%5==0) {
                suma += i;
            }
        }
        System.out.println(suma);
    }

    public static void factorial(int n) {
        int produs = 1;
        for(int i=1; i<=n; i++) {
            produs *= i;
        }
        System.out.println(produs);
    }

    public static boolean prim(int n) {
        if(n < 2) {
            return false;
        }
        else {
            for(int i=2; i<Math.sqrt(n); i++) {
                if(n%i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int get_nth_fib(int n) {
        int a1 = 1, a2 = 1, a3 = 1;
        for(int i=3; i<=n; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    public static void biggest_factor(int n) {
        int max = 0;
        for(int i=1; i<=n; i++) {
            if(n%i==0 && prim(i)) {
                max = i;
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
	// write your code here
        System.out.print("n = ");
        int n = in.nextInt();
        //display_odd();
        //compare_2_no();
        System.out.println("Problema 3");
        make_sum(n);
        System.out.println("Problema 4");
        factorial(n);
        System.out.println("Problema 5");
        System.out.println(prim(n));
        System.out.println("Problema 6");
        int x = get_nth_fib(n);
        System.out.println(x);
        System.out.println("Problema 7");
        biggest_factor(n);
    }
}
