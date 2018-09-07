package com.udemy.flightReservation.service;

import com.udemy.flightReservation.Dto.ReservationRequest;
import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.entity.Passenger;
import com.udemy.flightReservation.entity.Reservation;
import com.udemy.flightReservation.repository.FlightRepository;
import com.udemy.flightReservation.repository.PassengerRepository;
import com.udemy.flightReservation.repository.ReservationRepository;
import com.udemy.flightReservation.util.EmailUtil;
import com.udemy.flightReservation.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PdfGenerator pdfGenerator;
    @Autowired
    EmailUtil emailUtil;

    public Reservation bookFlight(ReservationRequest reservationRequest){
       try {
           Flight flight = flightRepository.findById(reservationRequest.getFlightId()).get();

           Passenger passenger = new Passenger();
           passenger.setFirstName(reservationRequest.getpFirstName());
           passenger.setLastName(reservationRequest.getpLastName());
           passenger.setEmail(reservationRequest.getpEmail());
           passenger.setPhone(reservationRequest.getpPhone());
           Passenger savedPassenger = passengerRepository.save(passenger);

           Reservation reservation = new Reservation();
           reservation.setCheckedIn(false);
           reservation.setFlight(flight);
           reservation.setPassenger(savedPassenger);
           Reservation savedReservation = reservationRepository.save(reservation);
           String filePath = "/home/prasad/reservation" + savedReservation.getId() + ".pdf";

           pdfGenerator.generateItinerary(savedReservation,filePath);
           return savedReservation;
       }catch (Exception e){
           e.printStackTrace();
       }

        return null;

    }

    public void sendItinerary(){

    }

}
