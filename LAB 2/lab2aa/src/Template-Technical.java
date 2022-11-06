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
class Technical extends Staff {
    private String skills;

    Technical(String staffid, String name, String phone, double salary, String skills) {
        super(staffid, name, phone, salary);
        this.skills = skills;
    }

    Technical(String skills) {
        this.skills = skills;  }

    String getSkills() {
        return this.skills;
    }

    void display() {
        System.out.println("Staff Details: Technical ");
        super.display();
        System.out.println("Skills: " + getSkills());
        System.out.println();
    }
}
