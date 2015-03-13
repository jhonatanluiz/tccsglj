/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util.mail;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import tcc.sglj.util.StringJoin;

/**
 *
 * @author jhonatan
 */
public class EmailMessage {

    private String to;
    private String cc;
    private String bcc;
    private String from;
    private String subject;
    private String text;

    public EmailMessage() {
        this.to = "";
        this.cc = "";
        this.bcc = "";
        this.from = "";
        this.subject = "";
        this.text = "";
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTo(String[] to) {
        this.to = StringJoin.join(Arrays.asList(to), ",");
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = StringJoin.join(Arrays.asList(bcc), ",");
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
    public void setCc(String[] cc) {
        this.cc = StringJoin.join(Arrays.asList(cc), ",");
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
