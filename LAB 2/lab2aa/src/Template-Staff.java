import java.util.Scanner;

/**
 * Design a superclass called Staff with details as StaffId, Name, Phone, Salary.
 * Extend this class by writing three subclasses namely
 * Teaching (domain, publications),
 * Technical (skills), and
 * Contract (period).
 * Write a Java program to read and display at least 3 staff objects of
 * all three categories.
 */
class Staff {
    private String staffid;
    private String name;
    private String phone;
    private double salary;

    public String getStaffid() {
        return this.staffid;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public double getSalary() {
        return this.salary;
    }

    public Staff() {
        this.staffid = "AI01";
        this.name = "AIML HoD";
        this.phone = "9876543210";
        this.salary = 100000.0;
    }

    Staff(String staffid, String name, String phone, double salary) {
        this.staffid = staffid;
        this.name = name;
        this.phone = phone;
        this.salary = salary;
    }

    void display() {
        System.out.println("Staffid = " + getStaffid() +
                ", Name = " + getName() +
                ", Phone = " + getPhone() +
                ", Salary = " + getSalary());
    }
}