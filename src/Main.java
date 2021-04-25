import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static ArrayList<BigInteger> A = new ArrayList<>();
    static ArrayList<BigInteger> R = new ArrayList<>();
    static ArrayList<BigInteger> Q = new ArrayList<>();
    static ArrayList<BigInteger> X = new ArrayList<>();
    static ArrayList<BigInteger> Y = new ArrayList<>();

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


        return a;
    }

    public static boolean millerRabin(BigInteger a, BigInteger p){
        boolean prime = false;
        BigInteger pMinusOne = p.subtract(BigInteger.ONE), d = BigInteger.ZERO;
        int S = 0;
        boolean dividable = true;
        while(dividable){
            if (pMinusOne.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
                pMinusOne = pMinusOne.divide(BigInteger.TWO);
                S++;
                System.out.println(pMinusOne);
            } else{
                d = pMinusOne;
                dividable = false;
            }
        }

        for (int i = 0; i < S; i++){
            a = a.pow(d.intValue()).mod(p);

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
        BigInteger p = BigInteger.valueOf(561);
        BigInteger q = BigInteger.valueOf(123);
        extendedEuclideanAlgorithm(p,q);
        millerRabin(BigInteger.TWO, p);


        System.out.println("Add meg mit szeretnél csináni:\nT esetén titkosít\nV esetén visszafejt");
    }
}