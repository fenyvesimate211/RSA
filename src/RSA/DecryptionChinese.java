package RSA;

import java.math.BigInteger;

public class DecryptionChinese {

    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger d;
    private BigInteger[] publicKeys;

    public DecryptionChinese(BigInteger p, BigInteger q, BigInteger e, BigInteger d, BigInteger[] publicKeys) {
        this.p = p;
        this.q = q;
        this.e = e;
        this.d = d;
        this.publicKeys = publicKeys;
    }

    public String decrypt(BigInteger message){
        BigInteger n = p.multiply(q);
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        BigInteger mp = FastModularExponent.modPow(message, dp, p);
                //message.pow(dp.intValue()).mod(p);
        BigInteger mq = FastModularExponent.modPow(message, dq, q);
                //message.pow(dq.intValue()).mod(q);

        BigInteger [] ypyq = ExtendedEuclideanAlgorithm.EEA(q, p);

        BigInteger decryptedM = mp.multiply(ypyq[0]).multiply(q).add(mq.multiply(ypyq[1]).multiply(p)).mod(n);
        byte[] allBytes = decryptedM.toByteArray();

        return new String(allBytes);
    }
}
