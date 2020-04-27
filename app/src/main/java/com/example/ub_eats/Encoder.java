package com.example.ub_eats;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class Encoder {

    public Encoder(){

    }

    public String[] encodePassword(String password) throws NoSuchAlgorithmException {
        //Encodes a password using SHA-512 and returns the result along with the salt used to create it
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[20];
        r.nextBytes(salt);
        //String string_salt = Base64.getEncoder().encodeToString(salt);
        //Salt hard-coded for now
        String string_salt = "2AxGUi/qgnHOZRuG2RaUMtLhe+Q=";
        String pass_and_hash = string_salt + password;
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(pass_and_hash.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();

        String encoded = Base64.getEncoder().encodeToString(hash);
        String[] salt_and_encoded = {string_salt, encoded};

        return salt_and_encoded;
    }

    public String decodePassword(String pass, String salt_value) throws NoSuchAlgorithmException {
        //Encodes a password given the salt
        String pass_and_hash = salt_value + pass;
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(pass_and_hash.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();

        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }
}
