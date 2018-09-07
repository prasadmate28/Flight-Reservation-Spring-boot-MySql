package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.Dto.ReservationUpdateRequest;
import com.udemy.flightReservation.entity.Reservation;
import com.udemy.flightReservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable Long id){
        return reservationRepository.findById(id).get();
    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        Reservation currReservation = reservationRepository.findById(request.getId()).get();
        currReservation.setCheckedIn(request.getCheckedIn());
        currReservation.setNumberOfBags(request.getNumberOfBags());
        return reservationRepository.save(currReservation);
    }
}
