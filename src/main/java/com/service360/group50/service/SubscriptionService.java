package com.service360.group50.service;

import com.service360.group50.entity.Subscription;
import com.service360.group50.entity.SubscriptionHistory;
import com.service360.group50.entity.SubscriptionPlan;
import com.service360.group50.repo.SubscriptionHistoryRepository;
import com.service360.group50.repo.SubscriptionPlanRepository;
import com.service360.group50.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private SubscriptionHistoryRepository subscriptionHistoryRepository;

    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findByUsers_userid(id);
    }


    // get Active subscribtion by userId
    public Subscription getActiveSubscribtionByUserId(Long userId) {
        Subscription activeSubscription = subscriptionRepository.findByUsers_userid(userId);
        Date currentDate = new Date();
        if (activeSubscription.getEndDate().before(currentDate)) {
            // Subscription has expired, move it to subscription history
            SubscriptionHistory subscriptionHistory = SubscriptionHistory.builder()
                    .startDate(activeSubscription.getStartDate())
                    .endDate(activeSubscription.getEndDate())
                    .createdAt(activeSubscription.getCreatedAt())
                    .subscriptionPlan(activeSubscription.getSubscriptionPlan())
                    .users(activeSubscription.getUsers())
                    .build();
            subscriptionHistoryRepository.save(subscriptionHistory);

            // Delete the expired subscription
            subscriptionRepository.delete(activeSubscription);

            return null;
        }
        return subscriptionRepository.findByUsers_userid(userId);
    }


    public SubscriptionPlan getSubscriptionPlan(Long planId) {
        return subscriptionPlanRepository.findById(planId).orElse(null);
    }

    public Subscription addOrUpdateSubscription(Long userId, Subscription newSubscription) {
        // Retrieve the user's current subscription
        Subscription currentSubscription = subscriptionRepository.findByUsers_userid(userId);

        // Check if there is an existing subscription
        if (currentSubscription != null) {
            // Save the current subscription to the subscription history
            SubscriptionHistory subscriptionHistory = SubscriptionHistory.builder()
                    .startDate(currentSubscription.getStartDate())
                    .endDate(currentSubscription.getEndDate())
                    .createdAt(currentSubscription.getCreatedAt())
                    .id(currentSubscription.getSubscriptionPlan().getId())
                    .subscriptionPlan(currentSubscription.getSubscriptionPlan())
                    .users(currentSubscription.getUsers())
                    .build();
            subscriptionHistoryRepository.save(subscriptionHistory);

            // Delete the current subscription
            subscriptionRepository.delete(currentSubscription);
        }

        // Save the new subscription
       return subscriptionRepository.save(newSubscription);
    }

    public SubscriptionPlan addSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    public List<SubscriptionHistory> getSubscriptionHistoryByUserId(Long userid) {
        return subscriptionHistoryRepository.findByUsers_userid(userid);
    }

    public SubscriptionHistory addSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
        return subscriptionHistoryRepository.save(subscriptionHistory);
    }
}
