package com.service360.group50.controller;

import com.service360.group50.dto.AdsDTO;
import com.service360.group50.dto.SubscriptionDTO;
import com.service360.group50.entity.*;
import com.service360.group50.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdvertiserController {

    @Autowired
    private AdvertiserService advertiserService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private NotificationService notificationService;



    @PostMapping("auth/createAd")
    public Ads createAd(
            @RequestParam("adsImages") MultipartFile[] adsImages,
            @RequestParam("adsName") String adsName,
            @RequestParam("category") String category,
            @RequestParam("price") String price,
            @RequestParam("warrantyMonths") String warrantyMonths,
            @RequestParam("description") String description,
            @RequestParam("area") String area,
            @RequestParam("delivery") String delivery,
            @RequestParam("role") String role,
            @RequestParam("userId") Long userId
    ) {
        String uploadDirectory = "src/main/resources/static/images/ads";
        if (role.equals("ADVERTISER")) {
                Ads ad = new Ads();
                ad.setAdsName(adsName);
                ad.setCategory(category);
                ad.setPrice(price);
                ad.setWarrantyMonths(warrantyMonths);
                ad.setDescription(description);
                ad.setArea(area);
                ad.setDelivery(delivery);
                ad.setStatus("Active");
                ad.setVerificationStatus("Pending");
                ad.setUser(userService.getUser(userId));

                String adsImagesString = "";
                for (MultipartFile imageFile : adsImages) {
                    try {
                        adsImagesString += imageService.saveImageToStorage(uploadDirectory, imageFile) + ",";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ad.setAdsImages(adsImagesString);

                Ads createdAd = advertiserService.CreateAd(ad);

                if(createdAd.getAdsId() != null ){
                    Notification notification = new Notification();
                    notification.setTitle(adsName+" Ad was Added");
                    notification.setMessage("You have created a new ad");
                    notification.setSenderName("Admin");
                    notification.setFEButton1("View");
                    notification.setFEButton1Link("Advertiser/Ads");
                    notification.setBEButton1("Disable");
                    notification.setBEButton1Link("disable/"+createdAd.getAdsId());
                    notification.setUsers(userService.getUser(userId));
                    notificationService.addNotification(notification);
                }

                return createdAd;
        }
        return null;
    }


    @PutMapping("auth/updateAd")
    public Ads updateAd(
            @RequestParam(value = "adsImages",required = false) MultipartFile[] adsImages,
            @RequestParam(value = "adsName",required = false) String adsName,
            @RequestParam(value = "category",required = false) String category,
            @RequestParam(value = "price",required = false) String price,
            @RequestParam(value = "warrantyMonths",required = false) String warrantyMonths,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "area",required = false) String area,
            @RequestParam(value = "delivery",required = false) String delivery,
            @RequestParam("role") String role,
            @RequestParam("userId") Long userId,
            @RequestParam("adsId") Long adsId
    ){
        if(role.equals("ADVERTISER")) {

//                String uploadDirectory = "src/main/resources/static/images/ads";
            Ads ad = advertiserService.getAd(adsId);
            if(adsName != null){
                ad.setAdsName(adsName);
            }

            if(category != null){
                ad.setCategory(category);
            }

            if(price != null){
                ad.setPrice(price);
            }

            if(warrantyMonths != null){
                ad.setWarrantyMonths(warrantyMonths);
            }

            if(description != null){
                ad.setDescription(description);
            }

            if(area != null){
                ad.setArea(area);
            }

            if(delivery != null){
                ad.setDelivery(delivery);
            }



//                String adsImagesString = "";
//                for (MultipartFile imageFile : adsImages) {
//                    try {
//                        adsImagesString += imageService.saveImageToStorage(uploadDirectory,imageFile) + ",";
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                ad.setAdsImages(adsImagesString);
            return advertiserService.updateAd(ad);
//                return ResponseEntity.status( HttpStatus.OK).body(new ResponseMessage("Ad updated successfully"));

        }

        return null;
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Ad could not be updated"));
    }


    @GetMapping("auth/getAds/{userId}")
    public List<AdsDTO> getAdsByUserId(@PathVariable Long userId)  {

        List<Ads> ads = advertiserService.getAdsByUserId(userId);
        List<AdsDTO> adsDTOList = new ArrayList<>(); // Create a list to hold AdsDTO objects

        Advertiser advertiser = advertiserService.getAdvertiserByUserId(userId);

        Subscription subscription = subscriptionService.getSubscription(userId);
        String subStatus = null;

        if (subscription != null) {

            if ("Expaired".equals(subscription.getStatus())) { // Note: typo corrected to "Expired"
                subStatus = "Expired";
            } else {
                subStatus = subscription.getId().toString();
            }
        } else {
            // Handle the case where there is no active subscription for the user
            subStatus = "No Active Subscription"; // You can set an appropriate message or value here
        }

        for (Ads ad : ads) {
            Users user = userService.getUser(ad.getUser().getUserid());
            AdsDTO adDTO = new AdsDTO();
            adDTO.setId(ad.getAdsId());
            adDTO.setAdsName(ad.getAdsName());
            adDTO.setCategory(ad.getCategory());
            adDTO.setPrice(ad.getPrice());
            adDTO.setWarrantyMonths(ad.getWarrantyMonths());
            adDTO.setDescription(ad.getDescription());
            adDTO.setArea(ad.getArea());
            adDTO.setDelivery(ad.getDelivery());
            adDTO.setStatus(ad.getStatus());
            adDTO.setVerificationStatus(ad.getVerificationStatus());
            adDTO.setUserId(userId);
            adDTO.setFirstName(user.getFirstname());
            adDTO.setLastName(user.getLastname());
            adDTO.setPlan(subStatus);
            if (user.getProfilePic() != null) {
                adDTO.setProfileImage(user.getProfilePic());
            }
            adDTO.setShopName(advertiser.getShopname());
            adDTO.setShopAddress(advertiser.getShopaddress());
            adDTO.setShopPhone(user.getPhonenumber());

            List<byte[]> adsimages = new ArrayList<>();

            try {
                List<byte[]> adsImages = getImages(ad.getAdsId());

                for (byte[] adsImage : adsImages) {
                    adsimages.add(adsImage);
                }

                adDTO.setAdsImages(adsimages);


            } catch (IOException e) {
                e.printStackTrace();
            }

            adsDTOList.add(adDTO); // Add the AdsDTO object to the list
        }

        return adsDTOList; // Return the list of AdsDTO objects
    }


    @GetMapping("auth/getAllAds")
    public List<AdsDTO> getAds(){
        List<AdsDTO> adsDTOList = new ArrayList<>(); // Create a list to hold AdsDTO objects

        List<Ads> ads = advertiserService.getAds();

        for (Ads ad : ads) {
            Users user = userService.getUser(ad.getUser().getUserid());
            Advertiser advertiser = advertiserService.getAdvertiserByUserId(ad.getUser().getUserid());
            AdsDTO adDTO = new AdsDTO();
            adDTO.setId(ad.getAdsId());
            adDTO.setAdsName(ad.getAdsName());
            adDTO.setCategory(ad.getCategory());
            adDTO.setPrice(ad.getPrice());
            adDTO.setWarrantyMonths(ad.getWarrantyMonths());
            adDTO.setDescription(ad.getDescription());
            adDTO.setArea(ad.getArea());
            adDTO.setDelivery(ad.getDelivery());
            adDTO.setStatus(ad.getStatus());
            adDTO.setVerificationStatus(ad.getVerificationStatus());
            adDTO.setUserId(user.getUserid());
            adDTO.setFirstName(user.getFirstname());
            adDTO.setLastName(user.getLastname());
            adDTO.setDate ( ad.getDate () );
            adDTO.setReason ( ad.getReason () );
//            adDTO.setPlan(subStatus);
            if (user.getProfilePic() != null) {
                adDTO.setProfileImage(user.getProfilePic());
            }
            adDTO.setShopName(advertiser.getShopname());
            adDTO.setShopAddress(advertiser.getShopaddress());
            adDTO.setShopPhone(user.getPhonenumber());

            List<byte[]> adsimages = new ArrayList<>();

            try {
                List<byte[]> adsImages = getImages(ad.getAdsId());

                for (byte[] adsImage : adsImages) {
                    adsimages.add(adsImage);
                }

                adDTO.setAdsImages(adsimages);


            } catch (IOException e) {
                e.printStackTrace();
            }

            adsDTOList.add(adDTO); // Add the AdsDTO object to the list
        }

        return adsDTOList; // Return the list of AdsDTO objects
    }





//    @GetMapping("auth/getAds")
//    public List<Ads> getAds()  {
//        return advertiserService.getAds();
//    }


    @GetMapping("auth/getAd/{adsId}")
    public Ads getAd(@PathVariable Long adsId)  {
        return advertiserService.getAd(adsId);
    }



//    @GetMapping("auth/getAdImages/{adsId}")
//    public ResponseEntity<byte[]> getImage(@PathVariable Long adsId) throws IOException {
//        String imageDirectory = "src/main/resources/static/images/ads";
//
//
//        String[] imageNames = advertiserService.getAdsImages(adsId).split(",");
////        imageNames = adImages.split(",");
//        List<byte[]> imageBytesList = new ArrayList<>();
//
//        for (String imageName : imageNames) {
//            byte[] imageBytes = imageService.getImage(imageDirectory, imageName);
//            imageBytesList.add(imageBytes);
//
//        }
////        System.out.println(imageBytesList);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG); // Adjust the content type as needed
//
//        // Assuming you want to return all images as a single byte array
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        for (byte[] imageBytes : imageBytesList) {
//            outputStream.write(imageBytes);
//        }
//        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
//
//    }


//    @GetMapping("auth/getAdImagesby/{adsId}")
    public List<byte[]> getImages(@PathVariable Long adsId) throws IOException {
        String imageDirectory = "src/main/resources/static/images/ads";


        String[] imageNames = advertiserService.getAdsImages(adsId).split(",");
//        imageNames = adImages.split(",");
        List<byte[]> imageBytesList = new ArrayList<>();

        for (String imageName : imageNames) {
            byte[] imageBytes = imageService.getImage(imageDirectory, imageName);
            imageBytesList.add(imageBytes);

        }

        return imageBytesList;

    }



    @DeleteMapping("auth/deleteAd/{adsId}")
    public void deleteAd(@PathVariable Long adsId){
        Ads ad = advertiserService.getAd(adsId);
        String[] imageNames = ad.getAdsImages().split(",");
        String imageDirectory = "src/main/resources/static/images/ads";
        for (String imageName : imageNames) {
            try {
                imageService.deleteImage(imageDirectory, imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        advertiserService.deleteAds(adsId);
    }


    @PutMapping("auth/disable/{adsId}")
    public void setStatusDisabled(@PathVariable Long adsId){
        advertiserService.setStatusDisabled(adsId);
    }


    @PutMapping("auth/enable/{adsId}")
    public void setStatusEnabled(@PathVariable Long adsId){
        advertiserService.setStatusEnabled(adsId);
    }

    // get subscription
@GetMapping("auth/subscriptionDetails/{userid}")
public SubscriptionDTO getSubscriptionDetails(@PathVariable Long userid) {
    Subscription subscription= subscriptionService.getActiveSubscribtionByUserId(userid);
    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
    if(subscription != null){
        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setStartDate(subscription.getStartDate().toString());
        subscriptionDTO.setEndDate(subscription.getEndDate().toString());
        subscriptionDTO.setStatus(subscription.getStatus());
        subscriptionDTO.setPlanName(subscription.getSubscriptionPlan().getName());
        subscriptionDTO.setPlanDescription(subscription.getSubscriptionPlan().getDescription());
        subscriptionDTO.setPlanPrice(subscription.getSubscriptionPlan().getPrice());
        subscriptionDTO.setUserId(userid);
    }
    return subscriptionDTO;
}


    // add subscription
    @PutMapping("auth/subscription/{userid}/{id}")
    public Subscription setSubscription(@PathVariable Long userid,@PathVariable Long id) {

        Users user = userService.getUser(userid);
        String role = user.getRole().name();
        if(role.equals("ADVERTISER")){
            String status = "Active";
            Subscription subscription = new Subscription();
            subscription.setStatus(status);
            subscription.setSubscriptionPlan(subscriptionService.getSubscriptionPlan(id));
            subscription.setUsers(userService.getUser(userid));
            // set current date and time
            subscription.setCreatedAt(new java.util.Date());
           return subscriptionService.addOrUpdateSubscription(userid,subscription);
        }

        return null;

    }

    // Add subscriptionPlan
    @PostMapping("auth/subscriptionPlan")
    public SubscriptionPlan addSubscriptionPlan( @RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestParam String price) {
        SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
        subscriptionPlan.setName(name);
        subscriptionPlan.setDescription(description);
        subscriptionPlan.setPrice(price);

        return subscriptionService.addSubscriptionPlan(subscriptionPlan);

    }


    // get subscription History
    @GetMapping("auth/subscriptionHistory/{userid}")
    public List<SubscriptionDTO> getSubscriptionHistory(@PathVariable Long userid) {
        List<SubscriptionHistory> subscriptionHistoryList = subscriptionService.getSubscriptionHistoryByUserId(userid);
        List<SubscriptionDTO> subscriptionDTOList = new ArrayList<>();
        for (SubscriptionHistory subscriptionHistory : subscriptionHistoryList) {
            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setId(subscriptionHistory.getId());
            subscriptionDTO.setStartDate(subscriptionHistory.getStartDate().toString());
            subscriptionDTO.setEndDate(subscriptionHistory.getEndDate().toString());
            subscriptionDTO.setCreatedAt(subscriptionHistory.getCreatedAt().toString());
            subscriptionDTO.setPlanName(subscriptionHistory.getSubscriptionPlan().getName());
            subscriptionDTO.setPlanPrice(subscriptionHistory.getSubscriptionPlan().getPrice());
            subscriptionDTO.setUserId(userid);
            subscriptionDTOList.add(subscriptionDTO);
        }
        return subscriptionDTOList;
    }

    // add subscription history
    @PutMapping("auth/subscriptionHistory/{userid}")
    public SubscriptionHistory setSubscriptionHistory(@PathVariable Long userid) {

        Users user = userService.getUser(userid);
        String role = user.getRole().name();
        if(role.equals("ADVERTISER")){
            // get current plan
            Subscription subscription = subscriptionService.getActiveSubscribtionByUserId(userid);
            String status = "Active";
            SubscriptionHistory subscriptionHistory = new SubscriptionHistory();
            subscriptionHistory.setUsers(userService.getUser(userid));
            subscriptionHistory.setSubscriptionPlan(subscription.getSubscriptionPlan());
            subscriptionHistory.setStartDate(subscription.getStartDate());

            subscriptionHistory.setEndDate(subscription.getEndDate());
            // set current date and time
            subscriptionHistory.setCreatedAt(new java.util.Date());
            return subscriptionService.addSubscriptionHistory(subscriptionHistory);
        }

        return null;

    }


}