import java.util.Scanner;

public class DivisibilityCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        
        if (num % 5 == 0 && num % 3 != 0) {
            System.out.println(num + " is divisible by 5 but not by 3");
        } else {
            System.out.println(num + " does not meet the condition");
        }
    }
}