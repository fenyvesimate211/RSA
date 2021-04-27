package RSA;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        KeyGen keys = new KeyGen();
        BigInteger[] publicKey = keys.getPublicKey();
        BigInteger p = keys.getP();
        BigInteger q = keys.getQ();
        BigInteger e = keys.getE();
        BigInteger d = keys.getD();

        System.out.println("T-t a titkosításhoz, V-t a visszafejtéshez, OK-t a befejezéshez:");
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();

        while (!action.equals("OK")){

            if (action.equals("T")){
                System.out.println("Titkosítani kívánt üzenet:");
                String c = in.nextLine();
                try {
                    Encription enc = new Encription(publicKey);
                    BigInteger encryptedMassage = enc.encrypt(c);

                    System.out.println("Titkosított szöveg: " + encryptedMassage);

                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                }

            } if(action.equals("V")){
                System.out.println("Adja meg a viszafejteni kívánt üzenetet");
                BigInteger m = in.nextBigInteger();

                DecryptionChinese dec = new DecryptionChinese(p, q, e, d, publicKey);
                String decryptedMassage = dec.decrypt(m);

                System.out.println("Visszafejtett üzenet: " + decryptedMassage);
            }
            action = in.nextLine();
        }
    }
}