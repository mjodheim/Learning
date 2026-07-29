package MiniApplications;

import java.util.Scanner;

public class Palindrome {
    public static void run(Scanner scanner) {
        String mot, result;
        boolean palindrome = true;

        System.out.print("Entrez votre mot: ");
        mot = scanner.nextLine();

        for (int i = 0; i <= mot.length()/2; i++) {
            if(mot.charAt(i) != mot.charAt(mot.length()-i-1)) {
                palindrome = false;
            }
        }
        
        result = palindrome ? " est" : " n'est pas";
        System.out.print(mot + result + " un palindrome.");
    }
}
