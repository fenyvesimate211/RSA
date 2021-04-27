package RSA;

import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {

    private ExtendedEuclideanAlgorithm(){}

    public static BigInteger[] EEA(BigInteger a, BigInteger b){

        BigInteger x = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastX = BigInteger.ONE;
        BigInteger lastY = BigInteger.ZERO;
        BigInteger temp;

        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastX.subtract(quotient.multiply(x));
            lastX = temp;

            temp = y;
            y = lastY.subtract(quotient.multiply(y));
            lastY = temp;
        }

        return new BigInteger[] { lastX, lastY };
    }
}
