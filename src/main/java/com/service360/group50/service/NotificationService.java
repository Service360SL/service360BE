package com.service360.group50.service;

import com.service360.group50.entity.Notification;
import com.service360.group50.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // add notification
    public Notification addNotification( Notification notification) {
        notification.setCreatedAt(new java.util.Date());
        notification.setStatus("UNREAD");
        return notificationRepository.save(notification);
    }

    // get notification by userId
    public List<Notification> getNotification(Long id) {
        List<Notification> notifications =new ArrayList<>();
        notificationRepository.findAll().forEach(notifications::add);
        return notifications;
    }


    // update notification
    public void updateNotification(Long id) {
        Notification notification= notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notification.setStatus("READ");
            notificationRepository.save(notification);
        }
    }


    public void updateAllNotification(Long id) {
        List<Notification> notifications =new ArrayList<>();
        notificationRepository.findAll().forEach(notifications::add);
        for (Notification n : notifications) {
            if(n.getUsers().getUserid().equals(id))
            {
                n.setStatus("READ");
                notificationRepository.save(n);
            }
        }
    }
}
