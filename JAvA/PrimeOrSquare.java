import java.util.Scanner;

public class PrimeOrSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        boolean isPrime = true;
        boolean isPerfectSquare = false;
        
        if (num < 2) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        
        int sqrt = (int) Math.sqrt(num);
        if (sqrt * sqrt == num) {
            isPerfectSquare = true;
        }
        
        if (isPrime) {
            System.out.println(num + " is a prime number");
        } else if (isPerfectSquare) {
            System.out.println(num + " is a perfect square");
        } else {
            System.out.println(num + " is neither prime nor perfect square");
        }
    }
}