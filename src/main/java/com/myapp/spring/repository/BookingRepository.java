package com.myapp.spring.repository;
import java.sql.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.Booking;


@Repository
public interface BookingRepository extends JpaRepository < Booking, Integer > {
	
	//Optional<Booking> findByfromcityAndtocityAnddate(String fromcity,String tocity,  Date date);
	
   
}

