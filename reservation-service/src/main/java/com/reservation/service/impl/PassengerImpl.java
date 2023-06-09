package com.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.entity.BookingCharges;
import com.reservation.entity.Passenger;
import com.reservation.repository.PassengerRepository;
import com.reservation.service.PassengerService;

import common.exception.NotFoundException;

@Service
public class PassengerImpl implements PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
		
	public PassengerImpl(PassengerRepository passengerRepository) {
		super();
		this.passengerRepository = passengerRepository;
	}

	@Override
	public List<Passenger> findAllPassengers() {	
		return passengerRepository.findAll();
	}

	@Override
	public Passenger findPassengerById(Long passId) {		
		return passengerRepository.findById(passId)
				.orElseThrow(()->new NotFoundException(String.format("Payment not found with ID %d",passId)));
	}

	@Override
	public Passenger createPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
		
	}

	@Override
	public Passenger updatePassenger(Passenger passenger) {
		Passenger exsistingPassenger = passengerRepository.findById(passenger.getPassId()).get();
		exsistingPassenger.setPassportNo(passenger.getPassportNo());
		exsistingPassenger.setFirstName(passenger.getFirstName());
		exsistingPassenger.setLastNname(passenger.getLastNname());
		exsistingPassenger.setSex(passenger.getSex());
		exsistingPassenger.setAge(passenger.getAge());
		exsistingPassenger.setAddress(passenger.getAddress());
		exsistingPassenger.setEmailId(passenger.getEmailId());
		exsistingPassenger.setPhoneNo(passenger.getPhoneNo());
		
		return passengerRepository.save(exsistingPassenger);
		
	}

	@Override
	public void deletePassenger(Long passId) {
		final Passenger passenger = passengerRepository.findById(passId)
				.orElseThrow(()->new NotFoundException(String.format("Payment not found with ID %d",passId)));
		
		passengerRepository.deleteById(passenger.getPassId());
	}

	@Override
	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	
}
