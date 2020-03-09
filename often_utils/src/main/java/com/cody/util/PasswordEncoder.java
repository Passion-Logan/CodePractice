package com.cody.util;

import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: PasswordEncoder
 *
 * @author PasswordEncoder
 * @Description: BCrypt 密码加密器
 * @date: 2020年3月9日 0009 14:21
 * @since JDK 1.8
 */
@Component
public class PasswordEncoder {

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    private final Log logger = LogFactory.getLog(getClass());

    private final int strength;

    private final SecureRandom random;

    public PasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength
     *            the log rounds to use
     */
    public PasswordEncoder(int strength) {
        this(strength, null);
    }

    /**
     * @param strength
     *            the log rounds to use
     * @param random
     *            the secure random instance to use
     *
     */
    public PasswordEncoder(int strength, SecureRandom random) {
        this.strength = strength;
        this.random = random;
    }

    public String encode(CharSequence rawPassword) {
        String salt;
        if (strength > 0) {
            if (random != null) {
                salt = BCrypt.gensalt(strength, random);
            } else {
                salt = BCrypt.gensalt(strength);
            }
        } else {
            salt = BCrypt.gensalt();
        }
        return BCrypt.hashpw(rawPassword.toString(), salt);
    }

    /**
     * 匹配密码是否正确
     *
     * @param rawPassword
     *            文明密码
     * @param encodedPassword
     *            加密后密码
     * @return
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            logger.warn("Empty encoded password");
            return false;
        }

        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            logger.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
