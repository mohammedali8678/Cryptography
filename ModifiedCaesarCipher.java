import java.util.Scanner;
public class ModifiedCaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the key (an integer):");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the text:");
        String text = scanner.nextLine();
        switch (choice) {
            case 1:
                String encryptedText = transform(text, key);
                System.out.println("Encrypted Text: " + encryptedText);
                break;
            case 2:
                String decryptedText = transform(text, -key);
                System.out.println("Decrypted Text: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        scanner.close();
    }
    private static String transform(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            char transformedChar = (char) (character + key);
            result.append(transformedChar);
        }
        return result.toString();
    }
}