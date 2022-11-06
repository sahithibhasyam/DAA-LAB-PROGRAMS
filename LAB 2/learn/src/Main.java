import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String name = "2022-06-13";
        String dob = String.valueOf(LocalDate.now());
        if (name.compareTo(dob) == 0) {
            System.out.println("AAAAAAAAA");
        } else {
            System.out.println("BBBBBBBBB");
        }

        StringBuilder n = new StringBuilder("Tony");
        System.out.println(n);

        System.out.println(n.charAt(0));
        n.setCharAt(0, 'P');
        System.out.println(n);
    }
}