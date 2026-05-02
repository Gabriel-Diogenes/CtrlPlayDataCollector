package com.ctrlplay.datacollector.services;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailService {

    private final String smtpHost;
    private final String smtpPort;
    private final String emailRemetente;
    private final String senhaRemetente;
    private final String emailDestinatario;

    public EmailService() {
        Dotenv dotenv = Dotenv.load();
        this.smtpHost       = dotenv.get("SMTP_HOST", "smtp.gmail.com");
        this.smtpPort       = dotenv.get("SMTP_PORT", "587");
        this.emailRemetente = dotenv.get("EMAIL_REMETENTE");
        this.senhaRemetente = dotenv.get("SENHA_EMAIL");
        this.emailDestinatario = dotenv.get("EMAIL_DESTINATARIO");
    }

    public void enviarPlanilha(String caminhoArquivo) {

        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            throw new RuntimeException("Arquivo não encontrado para envio: " + caminhoArquivo);
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.trust", smtpHost);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailRemetente, senhaRemetente);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
            message.setSubject("Planilha CtrlPlay - Contas a Receber");

            MimeBodyPart textoPart = new MimeBodyPart();
            textoPart.setText("Segue em anexo a planilha de contas a receber gerada automaticamente pelo CtrlPlay Data Collector.");

            MimeBodyPart anexoPart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(arquivo);
            anexoPart.setDataHandler(new DataHandler(fds));
            anexoPart.setFileName(arquivo.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textoPart);
            multipart.addBodyPart(anexoPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso para: " + emailDestinatario);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
}
