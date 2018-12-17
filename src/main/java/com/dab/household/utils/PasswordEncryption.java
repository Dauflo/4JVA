package com.dab.household.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    public static String encryption(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
