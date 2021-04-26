import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static BigInteger[] extendedEuclideanAlgorithm(BigInteger a, BigInteger b){
        BigInteger [] eea = new BigInteger[3];
        BigInteger x, x0 = BigInteger.ONE, x1 = BigInteger.ZERO,
                y, y0 = BigInteger.ZERO, y1 = BigInteger.ONE,
                r, q, s = BigInteger.ONE;

        while (!b.equals(BigInteger.ZERO)){
            r = a.mod(b); q = a.divide(b);
            a = b; b = r;
            x = x1; y = y1;
            x1 = q.multiply(x1).add(x0);
            y1 = q.multiply(y1).add(y0);
            x0 = x; y0 = y;
            s = s.negate();
        }
        eea[0] = s.multiply(x0); // x
        eea[1] = y0.negate();    // y
        eea[2] = a;              // d

        System.out.println("x= " + eea[0] + " y= " + eea[1] + " d=" + eea[2]);

        return eea;
    }

    public static BigInteger fastModularExponention(BigInteger a, BigInteger b, BigInteger m){
        String bInBinary = b.toString(2);
        StringBuilder bInBinaryRevers = new StringBuilder();
        bInBinaryRevers.append(bInBinary);
        bInBinaryRevers = bInBinaryRevers.reverse();

        ArrayList<BigInteger> powersOfA = new ArrayList<>();
        BigInteger power = a.mod(m);
        powersOfA.add(power);
        for (int i = 1; i < bInBinaryRevers.length(); i++) {
            power = (power.multiply(power)).mod(m);
            powersOfA.add(power);
        }

        ArrayList<BigInteger> powersOfARequired = new ArrayList<>();
        String binaryBIndex = "";
        BigInteger element;
        for (int i = 0; i < bInBinaryRevers.length(); i++) {
            binaryBIndex = "" + bInBinaryRevers.charAt(i);
            if (binaryBIndex.equals("1")) {
                element = powersOfA.get(i);
                powersOfARequired.add(element);
            }
        }

        BigInteger r = BigInteger.ONE;
        for (int i = 0; i < powersOfARequired.size(); i++) {
            element = powersOfARequired.get(i);
            r = r.multiply(element);
        }

        return r.mod(m);
    }

    public static boolean millerRabin(BigInteger p, BigInteger a){
        boolean prime = false;
        int S = 0;
        BigInteger result, resultMulti, exponent, d = p.subtract(BigInteger.ONE);

        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            S++;
        }

        result = fastModularExponention(a, d, p);

        if (result.equals(BigInteger.ONE)) {
            prime = true;
        } else {
            exponent = ((BigInteger.TWO).pow(0)).multiply(d);
            result = fastModularExponention(a, exponent, p);
            if (result.equals(BigInteger.valueOf(-1))) {
                prime = true;
            } else {
                for (int i = 1; i < S; i++) {
                    resultMulti = result.multiply(result);
                    result = resultMulti.mod(p); // 166
                    if (result.equals(BigInteger.valueOf(-1))) {
                        prime = true;
                    }
                }
            }
        }

        return prime;
    }

    public static void main(String[] args) {

        Random rnd = new Random();
//        BigInteger p = BigInteger.probablePrime(5, rnd);
//        BigInteger q = BigInteger.probablePrime(5, rnd);
//
//        System.out.println(p);
//        System.out.println(q);
        BigInteger p = BigInteger.valueOf(17);
        BigInteger q = BigInteger.valueOf(123);
        BigInteger a = BigInteger.valueOf(2);
        BigInteger b = BigInteger.valueOf(17);
        extendedEuclideanAlgorithm(p,q);
        System.out.println("mr: " + millerRabin(BigInteger.valueOf(17), BigInteger.valueOf(2)));
        System.out.println("fme: " + fastModularExponention(a, b, p));


        System.out.println("Add meg mit szeretnél csináni:\nT esetén titkosít\nV esetén visszafejt");
    }
}