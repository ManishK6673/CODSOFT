import java.util.Random;
import java.util.Scanner;

// 1) FIRST TASK - NUMBER GAME

public class NUMBER_GAME {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rm = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 5;
        int score = 0;

        System.out.println("Welcome to Guess the Number!");

        do {
            int numberToGuess = rm.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI've picked a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + attemptsLimit + " attempts to guess it.");

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the number (" + numberToGuess + ") correctly in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            } else {
                score++;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = sc.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }

        } while (true);

        System.out.println("Thanks for playing. Your total score is: " + score);
        sc.close();
    }
}

