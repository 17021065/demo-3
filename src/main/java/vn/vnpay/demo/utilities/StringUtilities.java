package vn.vnpay.demo.utilities;

import java.util.Random;

public class StringUtilities {

    public static String generateRandomString(Integer length) {
        int ASCII_0 = 48;
        int ASCII_9 = 57;
        int ASCII_A = 65;
        int ASCII_Z = 90;
        int ASCII_a = 97;
        int ASCII_z = 122;

        Random random = new Random();

        return random.ints(ASCII_0, ASCII_z + 1)
                .filter(i -> i <= ASCII_9 || (i >= ASCII_A && i <= ASCII_Z) || i >= ASCII_a)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
