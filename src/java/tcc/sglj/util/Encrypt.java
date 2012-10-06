/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jhonatan L S Santos
 */
public class Encrypt {

    private String stringHash;
    private String algorithm = "MD5";//"SHA-256";
    private MessageDigest digest;

    public Encrypt(String md5) {
        stringHash = md5;
    }

    public String encrypt() {
        String encrypt = null;
        try {
            digest = MessageDigest.getInstance(algorithm);
            BigInteger hashmd5 = new BigInteger(1, digest.digest(stringHash.getBytes()));
            encrypt = hashmd5.toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }
        return encrypt;
    }

    public boolean eguals(String hash) throws Exception{       
        return encrypt().equals(hash);
    }

    /**
     * @return the algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * @param algorithm the algorithm to set
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
