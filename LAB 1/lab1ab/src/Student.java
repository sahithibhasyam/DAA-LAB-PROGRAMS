
/**Create a Java class called Student with the following details 
 * as variables within it. (i) USN (ii) Name (iii) Programme (iv) Phone 
 * Write a Java program to create nStudent objects and print the 
 * USN, Name, Programme, and Phoneof these objects with suitable headings.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String usn;
    private String name;
    private String branch;
    private String phone;
    public Student(String usn, String name, String branch, String phone) {
        this.usn = usn;
        this.name = name;
        this.branch = branch;
        this.phone = phone;
    }
	
    public void display() {
        System.out.print("USN: " + this.usn + ", ");
        System.out.print("Name: " + this.name + ", ");
        System.out.print("Branch: " + this.branch + ", ");
        System.out.println("Phone:" + this.phone);
    }
	
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<Student>();
        String filename = args[0];
        System.out.println(filename);
        File infile = new File(filename);
        Scanner in = new Scanner(infile);
        int cnt = Integer.parseInt(in.nextLine());
        System.out.println("Total record: " + cnt);
        for (int i = 0; i < cnt; i++) {
            if (!in.hasNext()) {
                System.out.println("Input records =" + i + ", less than " + cnt);
                System.exit(-1);
            }
            String line = in.nextLine();
            String[] arr = line.split(",");
            if (arr.length < 4) {
                System.out.println("Insufficient input" + line);
                System.exit(-1);
            }

            students.add(new Student(arr[0], arr[1], arr[2], arr[3]));
        }
        if (in.hasNext()) {
            System.out.println("Input records is greater than " + cnt);
            System.exit(-1);
        }
        for (Student s : students) {
            s.display();
        }
    }
}
