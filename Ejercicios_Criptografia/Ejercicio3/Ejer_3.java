import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Ejer_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la cadena que quieras cifrar: ");
        String cadena = sc.nextLine();

        String clave = "1234567890123456789012345678901234567890123456789012345";

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            DESKeySpec kspec = new DESKeySpec(clave.getBytes());
            SecretKey ks = skf.generateSecret(kspec);

            Cipher cifrador = Cipher.getInstance("DES");
            cifrador.init(Cipher.ENCRYPT_MODE, ks);

            byte[] bytes = cadena.getBytes("UTF8");

            byte[] bytesCifrados = cifrador.doFinal(bytes);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytesCifrados.length; i++) {
                sb.append(String.format("%02X", bytesCifrados[i]));
            }

            System.out.println("La cadena original es: " + cadena);
            System.out.println("La cadena cifrada con DES es: " + sb.toString());

            cifrador.init(Cipher.DECRYPT_MODE, ks);

            byte[] bytesDescifrados = cifrador.doFinal(bytesCifrados);

            System.out.println("La cadena descifrada con DES es: " + new String(bytesDescifrados));

        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
