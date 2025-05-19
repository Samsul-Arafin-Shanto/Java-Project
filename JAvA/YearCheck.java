import java.util.Scanner;

public class YearCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        
        boolean isCentury = year % 100 == 0;
        boolean isLeap = (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
        
        System.out.println(year + " is " + (isCentury ? "" : "not ") + "a century year");
        System.out.println(year + " is " + (isLeap ? "" : "not ") + "a leap year");
    }
}