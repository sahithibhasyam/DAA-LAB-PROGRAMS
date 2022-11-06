import java.util.Scanner;

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
    public Staff(String staffid, String name, String phone, double salary) {
        this.staffid = staffid;
        this.name = name;
        this.phone = phone;
        this.salary = salary;
    }
    void display() {
        System.out.print("Staffid = " + getStaffid() +
                ", Name = " + getName() +
                ", Phone = " + getPhone() +
                ", Salary = " + getSalary());
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
    public String getDomain() {
       return this.domain;
    }
    public int getPublications() {
        return this.publications;
    }
    public void display() {
        super.display();
        System.out.print(" Domain: " + getDomain() + " Publications: " + getPublications());
        System.out.println();
    }
}
class Technical extends Staff {
    private String skills;
    public Technical(String staffid, String name, String phone, double salary, String skills) {
        super(staffid, name, phone, salary);
        this.skills = skills;
    }
    String getSkills() {
        return this.skills;
    }
    void display() {
        super.display();
        System.out.print(" Skills: " + getSkills());
        System.out.println();
    }
}
class Contract extends Staff {
    private int period; // no of years
    public Contract(String staffid, String name, String phone, double salary, int period) {
        super(staffid, name, phone, salary);
        this.period = period;
    }
    int getPeriod() {
        return this.period;
    }
    void display() {
        super.display();
        System.out.print(" Contract = " + getPeriod());
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
        int tcnt = 0, tecnt = 0, ccnt = 0;
        int n = 3;
        int choice;
        
        Scanner in = new Scanner(System.in);
        Teaching[] teachingStaff = new Teaching[n];
        Contract[] contractStaff = new Contract[n];
        Technical[] technicalStaff = new Technical[n];

        while (true) {
            System.out.println("""
                    1. Enter Teaching Staff Details
                    2. Enter Technical Staff Details
                    3. Enter Contract Staff Details
                    4. Display Teaching Staff Details
                    5. Display Technical Staff Details
                    6. Display Contract Staff Details
                    0. Exit
                    Enter Choice:\s""");
            while (true) {
                try {
                    choice = Integer.parseInt(in.next());
                    if (choice >= 6) {
                        System.out.println("Invalid Choice.");
                    }
                    break;
                } catch (NumberFormatException ignore) {
                    System.out.println("Invalid Choice.");
                    System.out.println("Enter your choice again: ");
                }
            }
            switch (choice) {
                case 1:
                    if (tcnt >= n) {
                        System.out.println("No more entries can be made");
                    } else {
                        System.out.println("Enter Teaching Staff Details");
                        System.out.println("Enter StaffId: ");
                        staffid = in.next();
                        System.out.print("Enter Name: ");
                        name = in.next();

                        System.out.print("Enter Phone number: ");
                        phone = in.next();
                        while (true) {
                            System.out.print("Enter Salary: ");
                            try {
                                salary = Double.parseDouble(in.next());
                                break;
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        System.out.print("Enter Domain: ");
                        domain = in.next();
                        while (true) {
                            System.out.print("Enter no. of publications: ");
                            try {
                                cntpub = Integer.parseInt(in.next());
                                break;
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        teachingStaff[tcnt] = new Teaching(staffid, name, phone, salary, domain, cntpub);
                        tcnt++;
                    }
                    break;
                case 2:
                    if (tecnt >= n) {
                        System.out.println("No more entries can be made");
                    } else {
                        System.out.println("Enter Technical Staff Details");
                        System.out.print("Enter StaffId: ");
                        staffid = in.next();
                        System.out.print("Enter Name: ");
                        name = in.next();
                        System.out.print("Enter Phone number: ");
                        phone = in.next();
                        while (true) {
                            System.out.print("Enter Salary: ");
                            try {
                                salary = Double.parseDouble(in.next());
                                break;
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        System.out.print("Enter Skills : ");
                        skills = in.next();
                        technicalStaff[tecnt] = new Technical(staffid, name, phone, salary, skills);
                        tecnt++;
                    }
                    break;
                case 3:
                    if (ccnt >= n) {
                        System.out.println("No more entries can be made");
                    } else {
                        System.out.println("Enter Contract Staff Details");
                        System.out.print("Enter StaffId: ");
                        staffid = in.next();
                        System.out.print("Enter Name: ");
                        name = in.next();
                        System.out.print("Enter Phone number: ");
                        phone = in.next();

                        while (true) {
                            System.out.print("Enter Salary: ");
                            try {
                                salary = Double.parseDouble(in.next());
                                break;
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        while (true) {
                            System.out.print("Enter contract period in years: ");
                            try {
                                period = Integer.parseInt(in.next());
                                break;
                            } catch (NumberFormatException ignore) {
                                System.out.println("Invalid input");
                            }
                        }
                        contractStaff[ccnt] = new Contract(staffid, name, phone, salary, period);
                        ccnt++;
                    }
                    break;
                case 4:
                    System.out.println("Teaching Staff Details");
                    if (tcnt == 0) {
                        System.out.println("No Teaching Staff Details Available");
                    } else {
                        for (int i = 0; i < tcnt; i++) {
                            teachingStaff[i].display();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Technical Staff Details");
                    if (tecnt == 0) {
                        System.out.println("No Technical Staff details available");
                    } else {
                        for (int i = 0; i < tecnt; i++) {
                            technicalStaff[i].display();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Contract Staff Details");
                    if (ccnt == 0) {
                        System.out.println("No contract staff details available");
                    } else {
                        for (int i = 0; i < ccnt; i++) {
                            contractStaff[i].display();
                        }
                    }
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}