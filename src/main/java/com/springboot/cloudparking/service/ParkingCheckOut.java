package com.springboot.cloudparking.service;

import java.time.LocalDateTime;

import com.springboot.cloudparking.model.Parking;

public class ParkingCheckOut {

	public static Double getBill(Parking parking) {
		return getBill(parking.getEntryDate(), parking.getExitDate());
	}

	private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
		return null;
	}
	
	
}
