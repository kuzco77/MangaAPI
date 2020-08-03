//package com.hust.manga.controllers;
//
//
//import com.hust.manga.core.AbstractController;
//import com.hust.manga.exceptions.NotFoundException;
//import com.hust.manga.model.campain.Campaign;
//import com.hust.manga.model.track.Tracking;
//import com.hust.manga.model.track.TrackingRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("tracks")
//public class TrackingController extends AbstractController {
//    @Autowired
//    TrackingServices trackingServices;
//    @Autowired
//    CampaignServices campaignServices;
//    @Autowired
//    CheckISPServices checkISPServices;
//
//    @PostMapping
//    public boolean createTracking(
//            HttpServletRequest httpRequest,
//            @RequestBody TrackingRequest request) {
//        Campaign campaign = campaignServices.findByCode(mangaConfig.getDbDefault(), request.campaignCode)
//                .orElseThrow(() -> new NotFoundException("Not found campaign  by code " + request.campaignCode));
//        Tracking tracking = new Tracking();
//        tracking.setIpAddress(httpRequest.getRemoteAddr());
//        tracking.setCampaignId(campaign.getId());
//        tracking.setIsp(checkISPServices.getIspByIP(mangaConfig.getDbDefault(), httpRequest.getRemoteAddr()));
//        ResponseEntity<String> json = new RestTemplate().getForEntity("http://ip-api.com/json/" + httpRequest.getRemoteAddr(), String.class);
//        System.out.println(json);
//        Tracking input = dtoServices.transfer(tracking, request);
//        putToRabbit(mangaConfig.getDbDefault(), "CREATE", input, Tracking.class);
//        return true;
//    }
//
//    @PutMapping("/{sessionTracking}/isClicked")
//    public boolean isClicked(@PathVariable("sessionTracking") String sessionTracking) {
//        Tracking tracking = trackingServices.findBySessionId(mangaConfig.getDbDefault(), sessionTracking)
//                .orElseThrow(() -> new NotFoundException("Not found session " + sessionTracking));
//        Tracking resultClick = trackingServices.setIsClick(mangaConfig.getDbDefault(), tracking);
//        if (resultClick != null){
//            putToRabbit(mangaConfig.getDbDefault(), "UPDATE", resultClick, Tracking.class);
//        }
//        return resultClick != null;
//    }
//}
