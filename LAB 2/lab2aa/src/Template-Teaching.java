
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


class Teaching extends Staff {
    private String domain;
    private int publications;

    public Teaching(String staffid, String name, String phone, double salary, String domain, int publications) {
        super(staffid, name, phone, salary);
        this.domain = domain;
        this.publications = publications;
    }

    public Teaching(String domain, int publications) {
        this.domain = domain;
        this.publications = publications;
    }

    public String getDomain() {
        return this.domain;
    }

    public int getPublications() {
        return this.publications;
    }

    public void display() {
        System.out.println("Staff Details: Teaching-");
        super.display();
        System.out.println("Domain: " + getDomain());
        System.out.println("Publications: " + getPublications());
    }
}
