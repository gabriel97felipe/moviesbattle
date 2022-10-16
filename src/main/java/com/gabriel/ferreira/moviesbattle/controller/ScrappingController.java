//package com.gabriel.ferreira.moviesbattle.controller;
//
//import com.mashape.unirest.http.Unirest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.http.HttpResponse;
//
//@RestController
//@RequestMapping("/webscrapping")
//public class ScrappingController {
//
//    @GetMapping()
//    public ResponseEntity<Object> getScrappingResponse() {
//        HttpResponse response = Unirest.post(API_URL)
//                .header("X-RapidAPI-Key", API_KEY)
//                .queryString("parameter", "value")
//                .field("parameter", "value")
//                .asJson();
//        return response;
//    }
//
//}
