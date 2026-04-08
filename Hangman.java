import java.util.*;

public class Hangman {

    // Word list - feel free to add more!
    private static final String[] WORDS = {
            "COMPUTER", "JAVASCRIPT", "DEVELOPER", "PROGRAMMING",
            "ALGORITHM", "TERMINAL", "HANGMAN", "MADAGASCAR", "ANTANANARIVO",
            "ELEPHANT", "GIRAFFE", "BANANA", "VEHICLE", "AIRPLANE", "MYSTERY"
    };

    private static final int MAX_ERRORS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String secretWord = WORDS[random.nextInt(WORDS.length)].toUpperCase();
        char[] displayedWord = new char[secretWord.length()];
        Arrays.fill(displayedWord, '_');

        Set<Character> usedLetters = new HashSet<>();
        int errors = 0;

        System.out.println("=== HANGMAN GAME ===\n");
        System.out.println("Guess the word! You have " + MAX_ERRORS + " wrong guesses allowed.\n");

        while (errors < MAX_ERRORS) {
            printHangman(errors);
            printWord(displayedWord);
            System.out.println("\nUsed letters: " + usedLetters);
            System.out.print("Enter a letter: ");

            String input = scanner.nextLine().toUpperCase().trim();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter!");
                continue;
            }

            char letter = input.charAt(0);

            if (usedLetters.contains(letter)) {
                System.out.println("You already used this letter!");
                continue;
            }

            usedLetters.add(letter);

            // Check if the letter is in the word
            boolean found = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    displayedWord[i] = letter;
                    found = true;
                }
            }

            if (!found) {
                errors++;
                System.out.println("Wrong letter!");
            } else {
                System.out.println("Good guess!");
            }

            // Check for win
            if (String.valueOf(displayedWord).equals(secretWord)) {
                printHangman(errors);
                System.out.println("\nCONGRATULATIONS! You guessed the word: " + secretWord);
                break;
            }
        }

        // Game over
        if (errors == MAX_ERRORS) {
            printHangman(errors);
            System.out.println("\nGAME OVER! The word was: " + secretWord);
        }

        scanner.close();
    }

    // Print the hangman ASCII art based on number of errors
    private static void printHangman(int errors) {
        System.out.println("\n");
        switch (errors) {
            case 0:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("   |   |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("  /|   |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("  /|\\  |");
                System.out.println("       |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("  /|\\  |");
                System.out.println("  /    |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 6:
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   O   |");
                System.out.println("  /|\\  |");
                System.out.println("  / \\  |");
                System.out.println("       |");
                System.out.println("=========");
                break;
            case 7: // fully hanged
                System.out.println("   +---+");
                System.out.println("   |   |");
                System.out.println("   X   |");
                System.out.println("  /|\\  |");
                System.out.println("  / \\  |");
                System.out.println("       |");
                System.out.println("=========");
                break;
        }
    }

    private static void printWord(char[] word) {
        System.out.print("Word to guess: ");
        for (char c : word) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
