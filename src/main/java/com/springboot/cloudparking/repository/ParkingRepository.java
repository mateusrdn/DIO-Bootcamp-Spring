package com.springboot.cloudparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.cloudparking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
