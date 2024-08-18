package com.urlico.Utils;

import com.urlico.Repository.UrlRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UrlUtils {
    private static int SHORT_URL_CHAR_SIZE=7;

    public static String convert(String longURL) {
        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(longURL.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomShortUrl(String longURL,UrlRepository urlRepository) {
        String hash=UrlUtils.convert(longURL);
        int numberOfCharsInHash=hash.length();
        int counter=0;
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            if(!urlRepository.existsUrlModelByShortURL(hash.substring(counter, counter+SHORT_URL_CHAR_SIZE))){
                return hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            }
            counter++;
        }
        return hash;
    }
}
