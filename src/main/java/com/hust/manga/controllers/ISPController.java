//package com.hust.manga.controllers;
//
//import com.hust.manga.core.AbstractController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("isp")
//public class ISPController extends AbstractController {
//    @Autowired
//    CheckISPServices checkISPServices;
//    @PostMapping("/")
//    public String getISPByIP(@RequestBody Test tes) {
//        System.out.println(tes.ip);
//        return checkISPServices.getIspByIP(mangaConfig.getDbDefault(), tes.ip);
//    }
//}
//
//class Test{
//    public String ip;
//}
