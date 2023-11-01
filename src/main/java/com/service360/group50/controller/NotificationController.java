package com.service360.group50.controller;

import com.service360.group50.dto.NotificationDTO;
import com.service360.group50.entity.Notification;
import com.service360.group50.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    // get notification by userId
    @GetMapping("auth/notification/{id}")
    public List<NotificationDTO> getNotification(@PathVariable Long id) {
        List<Notification> noti =  notificationService.getNotification(id);

        List <NotificationDTO> notiDTO = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Notification n : noti) {
            if(n.getStatus().equals("UNREAD"))
            {
                Date createdAtDate = n.getCreatedAt();
                String formattedCreatedAt = dateFormat.format(createdAtDate);

                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setId(n.getId());
                notificationDTO.setTitle(n.getTitle());
                notificationDTO.setMessage(n.getMessage());
                notificationDTO.setStatus(n.getStatus());
                notificationDTO.setFEButton1(n.getFEButton1());
                notificationDTO.setFEButton1Link(n.getFEButton1Link());
                notificationDTO.setBEButton1(n.getBEButton1());
                notificationDTO.setBEButton1Link(n.getBEButton1Link());
                notificationDTO.setBEButton2(n.getBEButton2());
                notificationDTO.setBEButton2Link(n.getBEButton2Link());
                notificationDTO.setCreatedAt(formattedCreatedAt);
                notificationDTO.setUserid(n.getUsers().getUserid());
                notiDTO.add(notificationDTO);
            }
        }
        return notiDTO;
    }

    // update notification status by notificationId
    @PutMapping("auth/notification/update/{id}")
    public void updateNotification(@PathVariable Long id) {
        notificationService.updateNotification(id);
    }

    // update notification status by userId
    @PutMapping("auth/notification/updateAll/{id}")
    public void updateAllNotification(@PathVariable Long id) {
        notificationService.updateAllNotification(id);
    }
}
