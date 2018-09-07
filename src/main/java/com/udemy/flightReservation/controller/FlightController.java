package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository  flightRepository;

    @RequestMapping
    public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to, @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "MM-dd-yyyy") Date dateOfDeparture, Model model){

        List<Flight> flights = flightRepository.findFlights(from, to, dateOfDeparture);
        model.addAttribute("flights",flights);
        return "displayFlights";
    }

    @RequestMapping("admin/showAddFlight")
    public String showAddFlight(){
        return "addFlight";
    }


}
