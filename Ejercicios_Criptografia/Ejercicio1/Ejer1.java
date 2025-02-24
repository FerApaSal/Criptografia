import java.security.Security;
import java.util.List;
import java.util.Set;

public class Ejer1 {
    public static void main(String[] args) {

        Set<String> messageDigest = Security.getAlgorithms("MessageDigest");
        Set<String> firmas = Security.getAlgorithms("Signature");
        Set<String> cifrado = Security.getAlgorithms("Cipher");

        // Algoritmos hash
        List<String> algoritmosHash = messageDigest.stream().toList();

        System.out.println("Algoritmos Hash");

        for (int i = 0; i < algoritmosHash.size(); i++) {
            System.out.println(algoritmosHash.get(i));
        }

        System.out.println("--------------------------------------");

        // Algoritmos firma
        List<String> algoritmosFirma = firmas.stream().toList();

        System.out.println("Algoritmos Firma");

        for (int i = 0; i < algoritmosFirma.size(); i++) {
            System.out.println(algoritmosFirma.get(i));
        }

        System.out.println("--------------------------------------");

        // Algoritmos cifrados
        List<String> algoritmosCifrado = cifrado.stream().toList();

        System.out.println("Algoritmos Cifrado");

        for (int i = 0; i < algoritmosCifrado.size(); i++) {
            System.out.println(algoritmosCifrado.get(i));
        }
    }
}