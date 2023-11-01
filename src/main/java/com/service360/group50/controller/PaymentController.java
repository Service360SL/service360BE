package com.service360.group50.controller;

import com.service360.group50.dto.PaymentDTO;
import com.service360.group50.entity.Payments;
import com.service360.group50.entity.Users;
import com.service360.group50.request.HashRequest;
import com.service360.group50.request.PayhereCallbackRequest;
import com.service360.group50.service.PaymentServices;
import com.service360.group50.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;


import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private UserService userService;


    private String merchantSecret = "MzcxNzUxMjA0MDQyMDY5OTI5ODIyNjU3MjE0MjM3OTQyNDAzNTcx";

    @PostMapping("auth/calculateHash")
    public PaymentDTO calculateHash(@RequestParam("amount") double amount) {

        String merahantID     = "1224501";
        String orderID = Long.toString(System.currentTimeMillis());
        double amounts         = amount;
        String currency       = "LKR";
        DecimalFormat df       = new DecimalFormat("0.00");
        String amountFormatted = df.format(amounts);
        String hash    = getMd5(merahantID + orderID + amountFormatted + currency + getMd5(merchantSecret));
        System.out.println("Generated Hash: " + hash);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(orderID);
        paymentDTO.setHash(hash);
//        **************************
        paymentDTO.setAmount(String.format("%.2f", amounts));


        return paymentDTO;

    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    @PostMapping("auth/PaymentNotification")
    public Payments paymentNotification(
            @RequestParam("order_id") String order_id,
            @RequestParam("amount") String payhere_amount,
            @RequestParam("sendUserId") Long sendUserId,
            @RequestParam(value = "receiveUserId", required = false) Long receiveUserId,
            @RequestParam("items") String items
    ) {
        System.out.println("Starting");
        Users sendUser = userService.getUser(sendUserId);

        if (sendUser != null) {
            Payments payments = new Payments();
            payments.setOrderId(order_id);
            payments.setAmount(payhere_amount);
            payments.setSenderId(sendUserId);
            payments.setReceiverId(Objects.requireNonNullElse(receiveUserId, 0L));
            payments.setPaymentName(items);
            payments.setPaymentStatus("Completed");
            payments.setPaymentMethod("Online");
            payments.setUserType(String.valueOf(sendUser.getRole()));
            System.out.println(payments.toString());
            return paymentServices.savePayment(payments);
        }
        return null;
    }




//    @PostMapping("auth/PaymentNotification")
//    public Payments paymentNotification(PayhereCallbackRequest request) {
//        String hash  = getMd5( request.getMerchantId()+request.getOrderId()+request.getPayhereAmount()+request.getPayhereCurrency()+request.getStatusCode()+ getMd5(merchantSecret));
//        System.out.println("Starting");
////        if (hash.equals(request.getMd5sig())) {
//            Payments payments = new Payments();
//            payments.setStatusCode(request.getStatusCode());
//            payments.setOrderId(request.getOrderId());
//            payments.setAmount(request.getPayhereAmount());
//            payments.setCardNo(request.getCard_no());
//            payments.setStatusMsg(request.getStatusMsg());
//            payments.setStatusCode(request.getStatusCode());
//            payments.setPaymentId(request.getPayherePaymentId());
//
//            System.out.println(payments.toString());
//
//            return paymentServices.savePayment(payments);
////        }
//
////        return null;
//
//    }



//    @PostMapping("auth/PaymentNotification")
//    public Payments paymentNotification(@RequestParam("merchant_id") String merchant_id,
//                                        @RequestParam("order_id") String order_id,
//                                        @RequestParam("payhere_amount") String payhere_amount,
//                                        @RequestParam("payhere_currency") String payhere_currency,
//                                        @RequestParam("status_code") String status_code,
//                                        @RequestParam("md5sig") String md5sig,
//                                        @RequestParam("payment_id") String payment_id,
//                                        @RequestParam("status_message") String status_message,
//                                        @RequestParam("card_no") String card_no
//
//    ) {
//        String hash  = getMd5(merchant_id + order_id + payhere_amount + payhere_currency+status_code + getMd5(merchantSecret));
//        System.out.println("Starting");
//        if (hash.equals(md5sig)) {
//            Payments payments = new Payments();
//            payments.setStatusCode(status_code);
//            payments.setOrderId(order_id);
//            payments.setAmount(payhere_amount);
//            payments.setCardNo(card_no);
//            payments.setStatusMsg(status_message);
//            payments.setStatusCode(status_code);
//            payments.setPaymentId(payment_id);
//
//            System.out.println(payments.toString());
//
//            return paymentServices.savePayment(payments);
//        }
//
//        return null;
//
//    }





}
