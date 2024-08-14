import java.util.Scanner;
public class PlayfairCipher {
    private static final int MATRIX_SIZE = 5;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the keyword:");
        String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        System.out.println("Enter the text:");
        String text = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        char[][] matrix = createMatrix(keyword);
        switch (choice) {
            case 1:
                String encryptedText = playfairCipher(text, matrix, true);
                System.out.println("Encrypted Text: " + encryptedText);
                break;
            case 2:
                String decryptedText = playfairCipher(text, matrix, false);
                System.out.println("Decrypted Text: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        scanner.close();
    }
    private static char[][] createMatrix(String keyword) {
        boolean[] used = new boolean[26];
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        int x = 0, y = 0;
        for (char c : keyword.toCharArray()) {
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                matrix[x][y] = c;
                y++;
                if (y == MATRIX_SIZE) {
                    y = 0;
                    x++;
                }
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!used[c - 'A']) {
                matrix[x][y] = c;
                y++;
                if (y == MATRIX_SIZE) {
                    y = 0;
                    x++;
                }
            }
        }
        return matrix;
    }
    private static String playfairCipher(String text, char[][] matrix, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        String preparedText = prepareText(text);
        for (int i = 0; i < preparedText.length(); i += 2) {
            char a = preparedText.charAt(i);
            char b = preparedText.charAt(i + 1);
            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : -1) + MATRIX_SIZE) % MATRIX_SIZE]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : -1) + MATRIX_SIZE) % MATRIX_SIZE]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encrypt ? 1 : -1) + MATRIX_SIZE) % MATRIX_SIZE][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : -1) + MATRIX_SIZE) % MATRIX_SIZE][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }
    private static String prepareText(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 1; i < sb.length(); i += 2) {
            if (sb.charAt(i) == sb.charAt(i - 1)) {
                sb.insert(i, 'X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }
    private static int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}