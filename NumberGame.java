package intern;
import java.util.*;
public class NumberGame {

    public static void main(String args []) {
        Scanner sc = new Scanner(System.in);
        int totalWin = 0;
        int totalRounds = 0;
        String choice;
        do {
            int randNum = (int)(Math.random() * 100) + 1;
            totalRounds++;
            System.out.println("Enter your guess (between 1 and 100): ");
            for (int i = 1; i < 6; i++) {
                int guess = sc.nextInt();
                if (guess == randNum) {
                    System.out.println("Correct! You guessed the number in"+ i +" attempt(s).");
                    totalWin++;
                    break;
                    
                }else if (i == 5) {
                    System.out.println("Sorry!! You are out of Attempts :( The correct Number was "+randNum+" ");
                }else if (guess < randNum) {
                    System.out.println("Your Guess is too low! You have only "+ (5-i) +" chance(s) left.");
                }else{
                    System.out.println("Your Guess is too High! You have only "+ (5-i) +" chance(s) left.");
                }
            }
            sc.nextLine();
            System.out.println("Do you want to play another round? (yes/no)");
            choice = sc.nextLine().toLowerCase();
            
        } while (!choice.equals("no"));
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Rounds won: " + totalWin);
        System.out.println("Thanks for playing! ");
        sc.close();
    }
}
