package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskys")
    public ResponseEntity<List<Whisky>> getAllWhiskys(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "distillery", required = false) Distillery distillery,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region
    ) {
        if (distillery != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery, age), HttpStatus.OK);
        }
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskys/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/whiskies/{id}")
    public ResponseEntity<Whisky> putDistillery(@RequestBody Whisky whisky,@PathVariable Long id) {
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/whiskys/{id}")
    public ResponseEntity<List<Whisky>> deleteWhisky(@PathVariable Long id) {
        whiskyRepository.deleteById(id);
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
