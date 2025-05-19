import java.util.Scanner;

public class ReverseFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        int[] fib = new int[N];
        
        if (N >= 1) fib[0] = 0;
        if (N >= 2) fib[1] = 1;
        
        for (int i = 2; i < N; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        
        System.out.println("Fibonacci series in reverse order:");
        for (int i = N-1; i >= 0; i--) {
            System.out.print(fib[i] + " ");
        }
    }
}