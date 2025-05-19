import java.util.Scanner;

public class DigitProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        int product = 1;
        
        while (num != 0) {
            product *= num % 10;
            num /= 10;
        }
        
        System.out.println("Product of digits: " + product);
    }
}