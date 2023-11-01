package com.service360.group50.email;

import com.service360.group50.entity.TrainingSession;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class StarterMail {
    @Autowired
    private QRCodeGenerator qrCodeGenerator;
    @Autowired
    private JavaMailSender javaMailSender;

    public void TrainingSessionInvitation(String recipientEmail, String qrCodeContent, TrainingSession trainingSession) throws Exception {
        // Generate the QR code as a byte array
        byte[] qrCodeBytes = qrCodeGenerator.generateQRCode(qrCodeContent, 500, 500);

        // Create a SimpleMailMessage object
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set the sender's email address
        helper.setFrom("service.360.50@gmail.com");

        // Set the recipient's email address
        helper.setTo(recipientEmail);

        // Create the HTML content for the email
        String htmlContent = "<html>" +
                "<body>" +
                "<h1>Training Session QR Code</h1>" +
                "<p>Here's your unique QR code for the training session. Please present this QR code to the training session to confirm your entrance.</p>" +
                "<h2>Training Session Details:</h2>" +
                "<ul>" +
                "<li><strong>Training Title:</strong> " + trainingSession.getTrainingtitle() + "</li>" +
                "<li><strong>Training Date:</strong> " + trainingSession.getTrainingdate() + "</li>" +
                "<li><strong>Training Start Time:</strong> " + trainingSession.getTrainingstarttime() + "</li>" +
                "<li><strong>Training End Time:</strong> " + trainingSession.getTrainingendtime() + "</li>" +
                "<li><strong>Training Location:</strong> " + trainingSession.getTraininglocation() + "</li>" +
                "<li><strong>Cost Paid:</strong> " + trainingSession.getTrainingcost() + " LKR</li>" +
                "</ul>" +
                "<img src='cid:qrcode' alt='QR Code'>" +
                "</body>" +
                "</html>";


        // Set the HTML content of the email
        helper.setText(htmlContent, true);

        // Attach the QR code as an attachment
        helper.addInline("qrcode", new ByteArrayResource(qrCodeBytes), "image/png");

        // Send the email
        javaMailSender.send(message);
    }

}
