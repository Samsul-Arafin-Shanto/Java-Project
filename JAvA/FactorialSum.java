import java.util.Scanner;

public class FactorialSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        long sum = 0;
        long factorial = 1;
        
        for (int i = 1; i <= N; i++) {
            factorial *= i;
            sum += factorial;
        }
        
        System.out.println("Sum of factorials from 1 to " + N + ": " + sum);
    }
}