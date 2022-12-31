import java.util.Scanner;

public class RouteCipherTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        RouteCipher cipher = new RouteCipher(key);
        String encrypt = cipher.encrypt("abort the mission, you have been spotted");
        System.out.println(encrypt);
        String decrypt = cipher.decrypt(encrypt);
        System.out.println(decrypt);
    }
}
