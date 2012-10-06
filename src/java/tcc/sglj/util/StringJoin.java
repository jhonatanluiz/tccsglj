/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author jhonatan
 */
public class StringJoin {

    public static String join(Collection collection, String delimiter) {
        StringBuilder string = new StringBuilder();
        Iterator i = collection.iterator();
        while (i.hasNext()) {
            string.append(i.next());
            if (i.hasNext()) {
                string.append(delimiter);
            }
        }

        return string.toString();
    }
}
