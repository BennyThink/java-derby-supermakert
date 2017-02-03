/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ***REMOVED***.util;


/**
 *
 * @author Benny~
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 对字符串求SHA1散列值
 *
 */
public class getSHA1 {

    /**
     *
     * @param str 传入的字符串
     * @return 散列值
     */
    public String getSHA(String str) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(getSHA1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
