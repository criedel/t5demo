package com.cupsoftware.carsharing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {

    private static final Logger log = LoggerFactory.getLogger(AppUtil.class);

    /**
     * @param password
     * @return the password turned into it's md5 hash sum
     * @throws NoSuchAlgorithmException
     */
    public static String toMD5Hash(String password) throws NoSuchAlgorithmException {

        if (StringUtils.isBlank(password)) {

            log.warn("Can't hash an empty password!");
            return StringUtils.EMPTY;
        }

        final MessageDigest m = MessageDigest.getInstance("MD5");
        final byte[] data = password.getBytes();
        m.update(data, 0, data.length);

        final BigInteger i = new BigInteger(1, m.digest());

        return String.format("%1$032X", i);
    }
}
