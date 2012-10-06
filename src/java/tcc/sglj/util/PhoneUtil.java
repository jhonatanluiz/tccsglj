/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.util;

/**
 *
 * @author jhonatan L S Santos
 */
public class PhoneUtil {

    private static String delimiter = ",";

    public static String[] explode(String phone) {
        String [] temp = phone.split(delimiter);
        return temp;
    }

}
