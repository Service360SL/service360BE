package com.service360.group50.service;

import com.service360.group50.entity.Ads;
import com.service360.group50.entity.Advertiser;
import com.service360.group50.entity.Subscription;
import com.service360.group50.entity.SubscriptionPlan;
import com.service360.group50.repo.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertiserService {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertiserRepository advertiserRepository;


    
    public Ads CreateAd(Ads ad) {
       return adsRepository.save(ad);
    }

    public Ads updateAd(Ads ad) {
        return adsRepository.save(ad);
    }

    public Ads getAd(Long id) {
        return adsRepository.findById(id).orElse(null);
    }

    public List<Ads> getAds(){
        List <Ads> ads = new ArrayList<>();
         adsRepository.findAll().forEach(ads::add);
         return ads;
    }

//    public List<Ads> getAdsByUserId(Long userId){
//        // find user by userId
//        return adsRepository.findByUser_userid(userId);
//    }

    // get adsImages by adsId
    public String getAdsImages(Long id) {
        return adsRepository.findById(id).orElse(null).getAdsImages();
    }

    // delete ads by adsId
    public void deleteAds(Long id) {
        adsRepository.deleteById(id);
    }

    // set status Disabled by adsId
    public void setStatusDisabled(Long id) {
        Ads ad = adsRepository.findById(id).orElse(null);
        ad.setStatus("Disabled");
        adsRepository.save(ad);
    }

    // set status Enabled by adsId
    public void setStatusEnabled(Long id) {
        Ads ad = adsRepository.findById(id).orElse(null);
        ad.setStatus("Active");
        adsRepository.save(ad);
    }


    public List<Ads> getAdsByUserId(Long userId) {
        return adsRepository.findByUser_userid(userId);
    }

    // get Advertiser by userId
    public Advertiser getAdvertiserByUserId(Long userId) {
        return advertiserRepository.findByUsers_userid(userId);
    }


}
