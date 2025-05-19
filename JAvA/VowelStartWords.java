import java.util.Scanner;

public class VowelStartWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        int count = 0;
        
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (word.length() > 0) {
                char firstChar = Character.toLowerCase(word.charAt(0));
                if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || 
                    firstChar == 'o' || firstChar == 'u') {
                    count++;
                }
            }
        }
        
        System.out.println("Number of words starting with vowel: " + count);
    }
}