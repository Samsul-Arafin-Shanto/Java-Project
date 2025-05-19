import java.util.Scanner;

public class MultiplesSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        int sum = 0;
        
        for (int i = num; i <= 100; i += num) {
            sum += i;
        }
        
        System.out.println("Sum of multiples up to 100: " + sum);
    }
}