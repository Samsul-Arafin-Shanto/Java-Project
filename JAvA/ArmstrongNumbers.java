public class ArmstrongNumbers {
    public static void main(String[] args) {
        System.out.println("Armstrong numbers between 1 and 1000:");
        
        for (int num = 1; num <= 1000; num++) {
            int original = num;
            int sum = 0;
            int digits = String.valueOf(num).length();
            
            while (original != 0) {
                int digit = original % 10;
                sum += Math.pow(digit, digits);
                original /= 10;
            }
            
            if (sum == num) {
                System.out.print(num + " ");
            }
        }
    }
}