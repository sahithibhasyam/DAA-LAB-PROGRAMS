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

    // default staff data
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
        System.out.println("Contract = " + getPeriod());
        System.out.println();
    }
}

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
        System.out.println();
    }
}

class Technical extends Staff {
    private String skills;

    Technical(String staffid, String name, String phone, double salary, String skills) {
        super(staffid, name, phone, salary);
        this.skills = skills;
    }

    Technical(String skills) {
        this.skills = skills;
    }

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

public class Lab2a {
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

        // currently, same staff details are used.
        // Try to get different staff details for Skills and Contract
        System.out.print("Enter Skills : ");
        skills = in.nextLine();
        System.out.print("Enter contract period in years: ");
        period = Integer.parseInt(in.nextLine());

        Teaching teacher = new Teaching(domain, cntpub);
        Technical tech = new Technical(skills);

        //Contract contract = new Contract(period);
        Contract contract = new Contract(staffid, name, phone, salary, period);
        System.out.println("");

        teacher.display();
        tech.display();
        contract.display();
    }
}

