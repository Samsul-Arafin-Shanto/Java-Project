import java.util.Scanner;

public class LCMCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        
        int gcd = 1;
        for (int i = 1; i <= num1 && i <= num2; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                gcd = i;
            }
        }
        
        int lcm = (num1 * num2) / gcd;
        System.out.println("LCM of " + num1 + " and " + num2 + ": " + lcm);
    }
}