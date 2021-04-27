package RSA;

import static RSA.FastModularExponent.modPow;
import java.math.BigInteger;

public class Encription {

    private BigInteger[] publicKeys;

    public Encription(BigInteger[] publickeys){
        publicKeys = publickeys;
    }

    public BigInteger encrypt(String message) throws Exception {

        byte[] allBytes = message.getBytes();
        BigInteger messageToEncrypt = new BigInteger(allBytes);

        if(messageToEncrypt.compareTo(publicKeys[0]) > 0)
            throw new Exception("Túl hosszú az üzenet");

        messageToEncrypt = modPow(messageToEncrypt,publicKeys[1],publicKeys[0]);

        return messageToEncrypt;
    }

}
