import java.util.Random;
import java.util.Scanner;

class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Generate a random number between 1 and 100
        int secretNumber = random.nextInt(100) + 1;
        
        int guess;
        do {
            // Prompt the user to enter their guess
            System.out.print("Guess the number between 1 and 100: ");
            guess = scanner.nextInt();
            
            // Compare the user's guess with the generated number
            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed the correct number.");
            } else if (guess < secretNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        } while (guess != secretNumber);
        
        scanner.close();
    }
}