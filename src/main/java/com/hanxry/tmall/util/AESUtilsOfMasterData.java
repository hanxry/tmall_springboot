package com.hanxry.tmall.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;


/**
 * AES 加密工具
 */
public class AESUtilsOfMasterData {
    private static final String AES = "AES";
    private static final String charset = "UTF-8";
    private static final int keysizeAES = 128;

    private static AESUtilsOfMasterData instance;

    private AESUtilsOfMasterData() {
    }


    private static AESUtilsOfMasterData getInstance() {
        if (instance == null) {
            instance = new AESUtilsOfMasterData();
        }
        return instance;
    }

    /**
     *
     */
    private String encode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }

    /**
     *
     */
    private String decode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }


    private String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keysize);
            } else {
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                random.setSeed(key.getBytes());
                kg.init(keysize, random);
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks, new SecureRandom(key.getBytes(charset)));
                byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);

                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }


    private String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 将密码加密返回数据库的存储的密码
     *
     * @param salt
     * @param password
     * @return
     * @throws Exception
     */
    public static String addSaltAgain(String salt, String password) {
        String realPassword = null;
        try {
            byte[] bytes = (salt + password).getBytes(StandardCharsets.UTF_16LE);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bytes);
            realPassword = Base64.encodeBase64String(messageDigest.digest());
        } catch (Exception e) {

        }
        return realPassword;
    }

////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 适用java与php的aes加密
     *
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, String key) {
        byte[] crypted = null;
        String res = "";
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
            res = parseByte2HexStr(crypted);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return res;
    }

    /**
     * 适用java与php的解密方法
     *
     * @param input
     * @param key
     * @return
     */
    public String decrypt(String input, String key) {
        byte[] output = null;
        String result = "";
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(parseHexStr2Byte(input));
            result = new String(output);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        String encode = AESUtilsOfMasterData.getInstance().encode("ggjjh@002", "ABC123456");
        System.out.println(encode);
        String or = AESUtilsOfMasterData.getInstance().decode("4B571AA546DDDD5ED26A7424167BE", "ABC123456");
        System.out.println(or);
        String ord = AESUtilsOfMasterData.getInstance().encode(or, "ABC123456");
        System.out.println("ord:" + ord);
    }
}