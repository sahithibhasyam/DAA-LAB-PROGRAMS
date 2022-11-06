import java.util.ArrayList;
import java.util.Scanner;

public class StudentStack {
    private String usn;
    private String name;
    private String branch;
    private long phone;
    ArrayList<StudentStack> stack;
    int top;

    public StudentStack (String usn, String name, String branch, long phone) {
        this.stack = new ArrayList<StudentStack>();
        this.top = -1;
        this.usn = usn;
        this.name = name;
        this.branch = branch;
        this.phone = phone;
    }

    public void push(StudentStack s) {
        this.top++;
        this.stack.add(s);
    }

    public StudentStack pop() {
        if (this.top < 0) {
            return null;
        }
        StudentStack item = stack.get(this.top);
        this.stack.remove(this.top--);
        return item;
    }

    public void display() {
        System.out.println(stack);
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1. Push\n2. Pop\n3. Display\n0. " +
                    "Exit\nEnter Choice: ");
            String choice = in.nextLine();
            int val = 0;
            switch (choice) {
                case 1:
                    System.out.print(" Enter integer value to push: ");
                    val = in.nextInt();
                    s.push(val);
                    break;
                case 2:
                    Integer value = s.pop();
                    if (value == null) {
                        System.out.println("Stack empty");
                    } else {
                        System.out.println("Popped value = " + value);
                    }
                    break;
                case 3:
                    System.out.println("Stack contents are:");
                    s.display();
                    break;
                case 0:
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice entered");
                    break;
            }
        }
    }
}
