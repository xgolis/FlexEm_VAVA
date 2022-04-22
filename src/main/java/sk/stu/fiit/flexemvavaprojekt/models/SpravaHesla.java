package sk.stu.fiit.flexemvavaprojekt.models;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Random;

public class SpravaHesla {


    public static byte[] salt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;

    }

    public static byte[] hash(String heslo,byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt);

        return (messageDigest.digest(heslo.getBytes(StandardCharsets.UTF_8)));

    }

    public static String vygenerovaneHeslo() {
        Random rand = new Random();
        String znaky = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder heslo = new StringBuilder();

        for (int i = 0; i < 8;i++) {
            int randomCislo = rand.nextInt(znaky.length());
            char znak = znaky.charAt(randomCislo);
            int randomCislo2 = rand.nextInt(2);
            if (randomCislo2 == 1 && randomCislo <= 25){
                znak = Character.toUpperCase(znak);
            }
            heslo.append(znak);
        }

        return heslo.toString();
    }
}