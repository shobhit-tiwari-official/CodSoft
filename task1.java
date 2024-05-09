import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        while (playAgain) {
            int lowerBound = 1;
            int upperBound = 100;
            int generatedNumber = lowerBound + random.nextInt(upperBound - lowerBound + 1);
            int attempts = 0;
            
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a number between " + lowerBound + " and " + upperBound + ". Guess it!");

            int guess;
            boolean guessedCorrectly = false;
            
            while (!guessedCorrectly) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else if (guess > generatedNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + generatedNumber + " correctly in " + attempts + " attempts!");
                    score += attempts;
                    guessedCorrectly = true;
                }
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }

        System.out.println("Your total score is: " + score);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
