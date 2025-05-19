import java.util.Scanner;

public class VowelDigitCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        int vowels = 0;
        int digits = 0;
        
        for (char c : input.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else if (Character.isDigit(c)) {
                digits++;
            }
        }
        
        System.out.println("Vowels: " + vowels);
        System.out.println("Digits: " + digits);
    }
}