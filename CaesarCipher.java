import java.util.Scanner;
public class CaesarCipher {
    private static final int KEY = 3;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the text:");
        String text = scanner.nextLine();
        switch (choice) {
            case 1:
                String encryptedText = encrypt(text, KEY);
                System.out.println("Encrypted Text: " + encryptedText);
                break;
            case 2:
                String decryptedText = decrypt(text, KEY);
                System.out.println("Decrypted Text: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        scanner.close();
    }
    private static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                char encryptedChar = (char) ((character - base + key) % 26 + base);
                result.append(encryptedChar);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    private static String decrypt(String text, int key) {
        return encrypt(text, 26 - key);
    }
}
