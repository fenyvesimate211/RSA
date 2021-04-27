package RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

import static RSA.FastModularExponent.modPow;

public class PrimeGeneration {

    private Integer bitLength;
    private Integer numberOfTests;

    public PrimeGeneration(Integer bitlength, Integer numberoftests){
        bitLength = bitlength;
        numberOfTests = numberoftests;
    }

    public BigInteger getProbablePrime() {

        BigInteger randomNum = generateSecureRandomNumber(bitLength);

        while (!MillerRabinTest(randomNum)){
            randomNum = generateSecureRandomNumber(bitLength);
        }

        return randomNum;
    }

    private boolean MillerRabinTest(BigInteger numberToTest) {

        if(numberToTest.compareTo(BigInteger.ONE) == 0)
            return false;
        else if(numberToTest.compareTo(BigInteger.valueOf('3')) < 0)
            return true;
        Integer s = 0;
        BigInteger d = numberToTest.subtract(BigInteger.ONE);

        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
            d = d.divide(BigInteger.TWO);
            s++;
        }

        for(int k = 0; k < numberOfTests; k++) {

            BigInteger a = randomBetween(BigInteger.TWO, numberToTest.subtract(BigInteger.ONE));
            BigInteger result = modPow(a,d, numberToTest);

            if (result.equals(BigInteger.ONE) || result.equals(numberToTest.subtract(BigInteger.ONE)))
                continue;

            int i = 0;
            for (; i < s; i++) {
                result = modPow(result,BigInteger.TWO,numberToTest);
                if (result.equals(BigInteger.ONE))
                    return false;
                else if (result.equals(numberToTest.subtract(BigInteger.ONE))) {
                    break;
                }
            }

            if(i == s)
                return false;
        }
        return true;
    }

    private BigInteger randomBetween(BigInteger min, BigInteger max){
        BigInteger a;

        do{
            a = new BigInteger(min.bitLength(), new SecureRandom());
        }while (a.compareTo(min) < 0|| a.compareTo(max) > 0);

        return a;
    }

    private BigInteger generateSecureRandomNumber(Integer bitLength){

        SecureRandom srg = new SecureRandom();
        return new BigInteger(bitLength,srg);
    }

}
