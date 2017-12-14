package com.rambler_spammer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

    private static final String ENCODING = "UTF-8";

    public static void main(String args[]) throws MessagingException, IOException, InterruptedException {
        ArrayList<String> recipientsList = new ArrayList<>();

        String recipients = "E:/addresses.txt";
        String subject = "Я РОДИЛСЯ!!!11!! ПЫЩЬ ПЫЩЬ АДИН АДИН!!";
        String content = "Я РОДИЛСЯ!!!11!! ПЫЩЬ ПЫЩЬ АДИН АДИН!!";
        String smtpHost = "smtp.rambler.ru";
        String smtpPort = "25";
        String from = "";                                                //Отправитель
        String login = "";                                               //Логин отправителя
        String password = "";                                            //Пароль ящика отправителя

        getRecipients(recipients, recipientsList);

        Properties props = getProperties(smtpHost, smtpPort);
        Session session = getSession(login, password, props);

        for (String to : recipientsList) {
            System.out.println(to);
            sendMessage(from, to, content, subject, session);
            Thread.sleep(4000);
        }
    }

    private static Properties getProperties(String smtpPort, String smtpHost) {
        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);
        return props;
    }

    private static Session getSession(String login, String password, Properties props) {
        Authenticator auth = new MyAuthenticator(login, password);
        return Session.getDefaultInstance(props, auth);
    }

    private static void sendMessage(String from, String to, String content, String subject, Session session)
            throws MessagingException, UnsupportedEncodingException {

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);
        System.out.println(to + "...OK");
    }

    private static void getRecipients(String fileName, ArrayList<String> array) throws IOException {
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
}