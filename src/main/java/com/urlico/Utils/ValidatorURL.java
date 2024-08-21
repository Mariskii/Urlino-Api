package com.urlico.Utils;

import java.net.URL;

public class ValidatorURL {
    public static boolean isUrlValid(String url) {
        try {
            new URL(url).toURI();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
