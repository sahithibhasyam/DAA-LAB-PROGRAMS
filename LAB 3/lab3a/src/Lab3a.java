
/**
 * Write a Java program to read two integers a and b. 
 * Compute a/b and print, when b is not zero. Raise an
 * exception when b is equal to zero.
 */

import java.util.Scanner;

public class Lab3a {
    public static void main(String[] args) {
        int a;
        int b;
        float res;
        Scanner in = new Scanner(System.in);
        while (true) {
            while (true) {
                System.out.println("Input value of a: ");
                try {
                    a = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException ignore) {
                    System.out.println("Invalid input");
                }
            }
            while (true) {
                System.out.println("Input value of b: ");
                try {
                    b = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException ignore) {
                    System.out.println("Invalid input");
                }
            }
            if (b != 0) {
                res = (float)a/b;
                System.out.println("a/b = " + res);
            } else {
                try {
                    throw new ArithmeticException("Integer divide by zero, error");
                } catch (Exception e) {
                    System.out.println("Denominator can't be zero: " + e);
                    System.exit(-1);
                }
            }
        }
    }
}