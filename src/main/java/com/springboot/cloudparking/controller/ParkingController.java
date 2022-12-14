package com.springboot.cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cloudparking.controller.DTO.ParkingCreateDTO;
import com.springboot.cloudparking.controller.DTO.ParkingDTO;
import com.springboot.cloudparking.controller.mapper.ParkingMapper;
import com.springboot.cloudparking.model.Parking;
import com.springboot.cloudparking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value =  "/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value =  "/{id}")
	@ApiOperation("Find By Id parking")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}
	
	
	@DeleteMapping(value =  "/{id}")
	@ApiOperation("Delete parking")
	public ResponseEntity delete(@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/create")
	@ApiOperation("Create parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.create(parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
		
	}
	
	@PutMapping(value =  "/{id}")
	@ApiOperation("Update parking")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
		
		Parking parkingUpdate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id, parkingUpdate);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	}
		
	@PostMapping("/{id}")
	@ApiOperation("CheckOut parking")
	public ResponseEntity<ParkingDTO> chechOut(@PathVariable String id){
		Parking parking = parkingService.checkOut(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	} 
	
}
