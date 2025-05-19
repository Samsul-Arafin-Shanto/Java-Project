import java.util.Scanner;

public class ArrayAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        double[] array = new double[size];
        
        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextDouble();
        }
        
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        
        double average = sum / size;
        System.out.println("Average: " + average);
    }
}