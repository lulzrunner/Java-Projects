package com.rambler_spammer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    static final String ENCODING = "UTF-8";

    private static void readUsingBufferedReader(String fileName, ArrayList<String> array) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            array.add(line);
        }
        br.close();
        fr.close();
    }

    public static void main(String args[]) throws MessagingException, IOException, InterruptedException {
        ArrayList<String> adressList = new ArrayList<String>();

        String subject = "Я РОДИЛСЯ!!!11!! ПЫЩЬ ПЫЩЬ АДИН АДИН!!";
        String content = "Я РОДИЛСЯ!!!11!! ПЫЩЬ ПЫЩЬ АДИН АДИН!!";
        String smtpHost = "smtp.rambler.ru";
        String address = "";                                             //Отправитель
        String login = "";                                               //Логин отправителя
        String password = "";                                            //Пароль ящика отправителя
        String smtpPort = "25";

        readUsingBufferedReader("E:/adresses.txt", adressList);

        for (String to : adressList) {
            System.out.println(to);
            sendSimpleMessage(login, password, address, to, content, subject, smtpPort, smtpHost);
            Thread.sleep(4000);
        }

    }

    public static void sendSimpleMessage(String login, String password, String from, String to, String content, String subject, String smtpPort, String smtpHost)
            throws MessagingException, UnsupportedEncodingException {
        Authenticator auth = new MyAuthenticator(login, password);

        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        Session session = Session.getDefaultInstance(props, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);
        System.out.println("Сообщение отправлено.");
    }
}

class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}