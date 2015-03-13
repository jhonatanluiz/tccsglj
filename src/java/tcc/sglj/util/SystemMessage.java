/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util;

import java.util.ResourceBundle;

/**
 *
 * @author jhonatan
 */
public class SystemMessage {

    public static String get(String key) {
        ResourceBundle resource = ResourceBundle.getBundle("message");
        return resource.getString(key);
    }
}
