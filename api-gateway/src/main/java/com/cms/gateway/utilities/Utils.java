package com.cms.gateway.utilities;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.util.Random;

public class Utils {

    private final static char[] ALPHABETIC = "abcdefghijklmnopqrstuvwxyz".toCharArray();

//    public static String encryptPassword(String password, String salt){
//        String encryptedPassword = "";
//
//        try {
//            ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
//            passwordEncryptor.setAlgorithm(Constants.ALGORITHM_SHA2_512);
//            passwordEncryptor.setPlainDigest(true);
//
//            encryptedPassword = passwordEncryptor.encryptPassword(salt + password);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return encryptedPassword;
//    }
//
//    public static String getRandomSalt() {
//        StringBuilder	sb		= new StringBuilder();
//
//        Random random	= new Random();
//        for (int i = 0; i < 8; i++) {
//            char c = ALPHABETIC[random.nextInt(ALPHABETIC.length)];
//            sb.append(c);
//        }
//
//        return sb.toString();
//    }
}
