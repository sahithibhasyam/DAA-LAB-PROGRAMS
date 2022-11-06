import java.util.Scanner;
import java.lang.String;

/**
 * Design a superclass called Staff with details as StaffId, Name, Phone, Salary.
 * Extend this class by writing three subclasses namely
 * Teaching (domain, publications),
 * Technical (skills), and
 * Contract (period).
 * Write a Java program to read and display at least 3 staff objects of
 * all three categories.
 */

class Lab2a {
    public static void main(String[] args) {
        String staffid;
        String name;
        String phone;
        double salary;
        String domain;
        int cntpub;
        String skills;
        int period;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter StaffId: ");
        staffid = in.nextLine();
        System.out.print("Enter Name: ");
        name = in.nextLine();
        System.out.print("Enter Phone number: ");
        phone = in.nextLine();
        System.out.print("Enter Salary: ");
        salary = Double.parseDouble(in.nextLine());

        System.out.print("Enter Teaching Domain: ");
        domain = in.nextLine();
        System.out.print("Enter number of publications: ");
        cntpub = Integer.parseInt(in.nextLine());

        System.out.print("Enter Skills : ");
        skills = in.nextLine();
        System.out.print("Enter contract period in years: ");
        period = Integer.parseInt(in.nextLine());

        Teaching teacher = new Teaching(staffid, name, phone, salary, domain, cntpub);
        Technical tech = new Technical(staffid, name, phone, salary, skills);

        //Contract contract = new Contract(period);
        Contract contract = new Contract(staffid, name, phone, salary, period);
        System.out.println("");

        teacher.display();
        tech.display();
        contract.display();
    }
}

