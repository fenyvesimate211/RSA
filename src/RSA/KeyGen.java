package RSA;

import java.math.BigInteger;

public class KeyGen {

    PrimeGeneration primeGen;

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger fiN;
    private BigInteger e;
    private BigInteger d;


    public KeyGen(){
        primeGen = new PrimeGeneration(1024 ,3);
        p = primeGen.getProbablePrime();
        System.out.println("p: "+p);
        q = primeGen.getProbablePrime();
        System.out.println("q: "+q);
        n = p.multiply(q);
        System.out.println("n: "+n);
        fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("fiN: "+fiN);
        e = calculateE();
        System.out.println("e: "+e);
        d = calculateD();
        System.out.println("d: "+d);
        System.out.println("");
        System.out.println("PK=("+n+", "+e+")");
        System.out.println("SK="+d);
    }

    public BigInteger calculateE(){
        BigInteger e = new BigInteger("65537");
        boolean notrelprime = true;
        BigInteger[] temp;

        while (notrelprime){

            temp = ExtendedEuclideanAlgorithm.EEA(e,fiN);
            BigInteger gcd = e.multiply(temp[0]).add(fiN.multiply(temp[1]));

            if(gcd.equals(BigInteger.ONE))
                notrelprime = false;
            else
                e = e.add(BigInteger.ONE);
        }
        return e;
    }

    public BigInteger calculateD(){

        BigInteger[] temp = ExtendedEuclideanAlgorithm.EEA(e,fiN);
        BigInteger d = temp[0];

        if(d.compareTo(BigInteger.ONE) > 0 && d.compareTo(fiN) < 0 ){
            return d;
        }
        else{
            return d.add(fiN);
        }
    }

    public BigInteger[] getPublicKey(){
        return new BigInteger[]{ n, e };
    }

    public BigInteger getP() { return p; }
    public BigInteger getQ() { return q; }
    public BigInteger getE() { return e; }
    public BigInteger getD() { return d; }

}
