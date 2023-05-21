package com.flight.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dto.ReservationRequest;
import com.flight.entities.Flight;
import com.flight.entities.Passenger;
import com.flight.entities.Reservation;
import com.flight.repositories.FlightRepository;
import com.flight.repositories.PassengerRepository;
import com.flight.repositories.ReservationRepository;
import com.flight.utilities.EmailUtil;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Optional<Flight> findById = flightRepo.findById(request.getId());
		Flight flight = findById.get();
		
		Passenger passenger  = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		
		passengerRepo.save(passenger);
		
		Reservation reservation  = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		reservationRepo.save(reservation);
		
		return reservation;
	}

}
