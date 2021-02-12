package com.mondris.dris.emailservice.demo.Service.Impl;

import com.mondris.dris.emailservice.demo.Dto.SendMailRequest;
import com.mondris.dris.emailservice.demo.Dto.SendMailResponse;
import com.mondris.dris.emailservice.demo.Service.EmailService;
import com.mondris.dris.emailservice.demo.Util.Exception.CustomErrorClass.IllegalArgumentException;
import com.mondris.dris.emailservice.demo.Util.MailConfig.SendMailConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private SendMailConfig sendMailConfig;
    private JavaMailSenderImpl javaMailSender;
    private String baseDir = "Email-Service/src/main/java/com/mondris/dris/emailservice/demo/Util/MailContents/";
    private String apiRoute = "/sendEmail";


    @Override
    public ResponseEntity<SendMailResponse> sendEmail(SendMailRequest request) throws Exception {

        String getMessageTemplatePath = getMessageBodyTemplatePath(request);

        if (getMessageTemplatePath == null) {
            throw new IllegalArgumentException("Invalid Message Body Template Name", apiRoute);
        }

        final JavaMailSenderImpl javaMailSenderImpl = createMailSender(request);

        final MimeMessage mailMessage = createMailMessage(request);

        // send the message
        javaMailSenderImpl.send(mailMessage);

        final SendMailResponse sendMailResponse = createResponseDtoObject(request);

        return new ResponseEntity<>(sendMailResponse, sendMailResponse.getHttpStatus());

    }


    private String getMessageBodyTemplatePath(SendMailRequest request) {

        String messageTemplateFileName = null;

        switch (request.getMessageBodyTemplateName()) {

            case "emailConfirmation":
                messageTemplateFileName = "emailConfirmation.html";
                request.setSubject("Email Verification Notification");
                break;

            case "forgotPassword":
                messageTemplateFileName = "Util/forgotPassword.html";
                request.setSubject("Change Password Email Notification");
                break;

            case "requestApproved":
                messageTemplateFileName = "Util/requestApproved.html";
                request.setSubject("Request Approval Notification");
                break;
        }

        return messageTemplateFileName;
    }


    // create  a mail sender
    private JavaMailSenderImpl createMailSender(SendMailRequest request) {

        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(sendMailConfig.getHost());
        javaMailSender.setPort(sendMailConfig.getPort());
        javaMailSender.setUsername(sendMailConfig.getUsername());
        javaMailSender.setPassword(sendMailConfig.getPassword());

        return javaMailSender;

    }

    // create an email instance
    private MimeMessage createMailMessage(SendMailRequest request) throws MessagingException, IOException {

        String emailTemplatePath = baseDir + request.getMessageBodyTemplateName() + ".html";

        Path filePath = Paths.get(emailTemplatePath);
        String emailContent = String.format(new String(Files.readString(filePath)), request.getReceiverFirstName(),
                request.getSenderFullName());

        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage);
        mimeMessageHelper.setFrom(request.getFromEmail());
        mimeMessageHelper.setTo(request.getToEmail());
        mimeMessageHelper.setSubject(request.getSubject());
        mimeMessageHelper.setText(emailContent, true);

        return mailMessage;
    }

    public SendMailResponse createResponseDtoObject(SendMailRequest request) {

        SendMailResponse sendMailResponse = new SendMailResponse();
        sendMailResponse.setHttpStatus(HttpStatus.OK);
        sendMailResponse.setMessage("Email Sent To " + request.getToEmail());

        return sendMailResponse;
    }
}
