package com.indianrailways.trainserviceapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainController {

    public ResponseEntity<?> getTrainsBetweenTwoStations (String fromStation, String toStation) {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Error in processing request !!!");
        }
    }
}
