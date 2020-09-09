package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "whiskyAge", required = false) Integer whiskyAge
    ) {
        if (region != null) {
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }
        if (whiskyAge != null){
            return new ResponseEntity(distilleryRepository.findByWhiskiesAge(whiskyAge), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id) {
        return new ResponseEntity(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery) {
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/distilleries/{id}")
    public ResponseEntity<List<Distillery>> deleteDistillery(@PathVariable Long id) {
        distilleryRepository.deleteById(id);
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

}
