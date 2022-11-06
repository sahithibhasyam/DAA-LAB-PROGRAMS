import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.Scanner;

public class Customer {
    private String name;
    private String dob;
    public static final int[] daysinmonth = {31,28,30,29};
    public Customer(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }
    public void display(String[] arrdob) {
        System.out.println(name +","+ arrdob[0]+","+arrdob[1]+","+arrdob[2]);
    }
    public static boolean validate(String dob) {
        String[] arr = dob.split("/");
        if (arr.length != 3) {
            return false;
        }
        int dd,mm,yyyy;
        while(true) {
            try {
                dd = Integer.parseInt(arr[0]);
                mm = Integer.parseInt(arr[1]);
                yyyy = Integer.parseInt(arr[2]);
                break; // will only get to here if input was a double
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
                System.exit(-1);
            }
        }
        if(yyyy>=1000 && yyyy<=YearMonth.now().getYear()) {
            if (yyyy==YearMonth.now().getYear() && mm>=YearMonth.now().getMonthValue()) {
                if(dd>MonthDay.now().getDayOfMonth()) {
                    return false;
                }
                return true;
            } else if(mm>=1 && mm<=12) {
                //check days
                if((dd>=1 && dd<=daysinmonth[0])
                        && (mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12)) {
                    return true;
                } else if((dd>=1 && dd<=daysinmonth[2])
                        && (mm==4 || mm==6 || mm==9 || mm==11)) {
                    return true;
                } else if((dd>=1 && dd<=daysinmonth[1])
                        && (mm==2)) {
                    return true;
                } else if(dd>=1 && dd==daysinmonth[3]
                        && mm==2 && (yyyy%400==0) || ((yyyy%4==0) && (yyyy%100!=0))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
    public static void main(String[] args) throws FileNotFoundException {
        String name;
        Customer customer;
        String filename = args[0];
        System.out.println(filename);
        File infile = new File(filename);
        Scanner in = new Scanner(infile);
        if (!in.hasNext()) {
            System.out.println("Insufficient input.");
            System.exit(-1);
        }
        name = in.nextLine();
        System.out.println("Name: " + name);
        if (!in.hasNext()) {
            System.out.println("Insufficient input.");
            System.exit(-1);
        }
        String dob = in.nextLine();
        String[] arrdob = dob.split("/");
        if (in.hasNext()) {
            System.out.println("Excess Input.");
            System.exit(-1);
        }
        if (validate(dob)) {
            customer = new Customer(name, dob);
            customer.display(arrdob);
        } else {
            System.out.println("Invalid date :" + dob);
        }
    }
}