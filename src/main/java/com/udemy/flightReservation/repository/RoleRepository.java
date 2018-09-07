package com.udemy.flightReservation.repository;

import com.udemy.flightReservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
