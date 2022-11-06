/**
 * Design a superclass called Staff with details as StaffId, Name, Phone, Salary.
 * Extend this class by writing three subclasses namely
 * Teaching (domain, publications),
 * Technical (skills), and
 * Contract (period).
 * Write a Java program to read and display at least 3 staff objects of
 * all three categories.
 */
import java.util.Scanner;


class Contract extends Staff {
    private int period; // no of years


    public Contract(String staffid, String name, String phone, double salary, int period) {
        super(staffid, name, phone, salary);
        this.period = period;
    }

    public Contract(int period) {
        this.period = period;
    }

    int getPeriod() {
        return this.period;
    }

    void display() {
        System.out.println("Staff details: Contract");
        super.display();
        System.out.println("Period: " + getPeriod());
    }
}

