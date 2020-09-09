package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

    List<Whisky> findByYear(int year);
    // Get all the whisky from a particular distillery that's a specific age
    List<Whisky> findByDistilleryAndAge(Distillery distillery, int age);
    //Get all the whisky from a particular region
    List<Whisky> findByDistilleryRegion(String Region);
}
