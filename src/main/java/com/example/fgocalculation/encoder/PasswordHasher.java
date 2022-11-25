package com.example.fgocalculation.encoder;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHasher {
    
    public String hasher(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA3-256");
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] pwBytes = password.getBytes();
        byte[] pwBytesHash = messageDigest.digest(pwBytes);
        BigInteger bigInteger = new BigInteger(1, pwBytesHash);
        int pwHashAddition = 0;
        for(byte pwByteHash : pwBytesHash) {
            pwHashAddition += (int)pwByteHash;
        }
        byte[] pwBytes_2 = ByteBuffer.allocate(4).putInt(pwHashAddition).array();
        byte[] pwBytesHash_2 = messageDigest.digest(pwBytes_2);
        BigInteger bigInteger_2 = bigInteger.add(new BigInteger(1, pwBytesHash_2));
        return String.format(String.format("%040x", bigInteger_2));
    }
}
