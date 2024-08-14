import java.util.Scanner;
public class VigenereCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the keyword:");
        String keyword = scanner.nextLine().toUpperCase();
        System.out.println("Enter the text:");
        String text = scanner.nextLine().toUpperCase();
        switch (choice) {
            case 1:
                String encryptedText = vigenereCipher(text, keyword, true);
                System.out.println("Encrypted Text: " + encryptedText);
                break;
            case 2:
                String decryptedText = vigenereCipher(text, keyword, false);
                System.out.println("Decrypted Text: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        scanner.close();
    }
    private static String vigenereCipher(String text, String keyword, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int keywordLength = keyword.length();
        int keyIndex = 0;
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = 'A';
                int key = keyword.charAt(keyIndex % keywordLength) - base;
                if (!encrypt) {
                    key = -key;
                }
                char transformedChar = (char) ((character - base + key + 26) % 26 + base);
                result.append(transformedChar);

                keyIndex++;
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}