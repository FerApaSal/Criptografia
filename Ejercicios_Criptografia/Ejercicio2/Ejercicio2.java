import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejercicio2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String texto = "Texto original";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(texto.getBytes(StandardCharsets.UTF_8));
        byte[] hash = md.digest();

//        String hashGenerado = new String(hash);

        StringBuilder factory = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            factory.append(String.format("%02X", hash[i]));
        }

        System.out.println("Encriptación SHA-256");
        System.out.println("El texto original es: " + texto);
        System.out.println("El texto cifrado es: " + factory.toString() + "\n");


        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(texto.getBytes(StandardCharsets.UTF_8));
        byte[] hashMD5 = md5.digest();

        StringBuilder factory2 = new StringBuilder();

        for (int i = 0; i < hashMD5.length; i++) {
            factory2.append(String.format("%02X", hashMD5[i]));
        }

        System.out.println("Encriptación MD5");
        System.out.println("El texto original es: " + texto);
        System.out.println("Texto cifrado es: " + factory2.toString());
    }
}
