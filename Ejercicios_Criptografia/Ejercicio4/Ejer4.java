import java.io.File;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase principal para demostrar algoritmos de firma y firmado de texto.
 */
public class Ejer4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("** ALGORITMOS DE FIRMA Y FIRMADO DE TEXTO **");
        int opcion = 0;

        do {
            System.out.println("1. Sabre altorimos de firma de los 'provider'.");
            System.out.println("2. Firmar un texto y verificar su firma.");
            System.out.println("0. Salir.");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    hallarAlgoritmosDisponibles();
                    break;
                case 2:
                    firmarYVerificarFirma();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Muestra los algoritmos de firma disponibles de los proveedores de seguridad.
     */
    public static void hallarAlgoritmosDisponibles() {
        Provider[] algoritmos = Security.getProviders();

        for (Provider provider : algoritmos) {
            Set<Provider.Service> conjuntos = provider.getServices();
            conjuntos.iterator().forEachRemaining(System.out::println);
        }
    }

    /**
     * Firma un texto de prueba y verifica su firma.
     */
    public static void firmarYVerificarFirma() {
        String texto = "Texto de prueba para firmar y verificar.";
        KeyPair clave = Ejer4.generarClaves();

        byte[] textoFirmado = Ejer4.hacerFirma(texto.getBytes(), clave.getPrivate());

        if (Ejer4.verificarFirma(texto.getBytes(), clave.getPublic(), textoFirmado)) {
            System.out.println("Firma realizada.");
        } else {
            System.out.println("Firma incorrecta.");
        }
    }

    /**
     * Genera un par de claves (pública y privada) usando el algoritmo DSA.
     *
     * @return El par de claves generado.
     */
    public static KeyPair generarClaves() {
        KeyPair claves = null;

        try {
            KeyPairGenerator generador = KeyPairGenerator.getInstance("DSA");
            claves = generador.genKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claves;
    }

    /**
     * Firma los datos proporcionados usando la clave privada.
     *
     * @param datos Los datos a firmar.
     * @param clave La clave privada para firmar los datos.
     * @return Los datos firmados.
     */
    public static byte[] hacerFirma(byte[] datos, PrivateKey clave) {
        byte[] firmado = null;

        try {
            Signature firma = Signature.getInstance("SHA256withDSA");

            firma.initSign(clave);

            firma.update(datos);

            firmado = firma.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return firmado;
    }

    /**
     * Verifica la firma de los datos proporcionados usando la clave pública.
     *
     * @param texto Los datos originales.
     * @param clave La clave pública para verificar la firma.
     * @param textoFirmado Los datos firmados.
     * @return true si la firma es válida, false en caso contrario.
     */
    public static boolean verificarFirma(byte[] texto, PublicKey clave, byte[] textoFirmado) {
        try {
            Signature firma2 = Signature.getInstance("SHA256withDSA");
            String cadena = Base64.getEncoder().encodeToString(textoFirmado);
            System.out.println("El texto firmado es: " + cadena);

            firma2.initVerify(clave);

            firma2.update(texto);

            return firma2.verify(textoFirmado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verificarFichero() throws SignatureException {
        File signed = new File("fichero.txt");
        try (Scanner scanner = new Scanner(signed)) {
            String texto = scanner.nextLine();
            String firmaBase64 = scanner.nextLine();

            byte[] textoBytes = texto.getBytes();
            byte[] firmaBytes = Base64.getDecoder().decode(firmaBase64);

            KeyPair clave = Ejer4.generarClaves();
            return Ejer4.verificarFirma(textoBytes, clave.getPublic(), firmaBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}