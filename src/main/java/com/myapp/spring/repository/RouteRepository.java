package com.myapp.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.RouteModel;

// Annotation is to identify that this is spring managed bean
// This is a data repository class
// 

@Repository
public interface RouteRepository extends JpaRepository<RouteModel, Integer>{
	//select * from product where price>
	
	@Query(value="SELECT * FROM Route ORDER BY Route_id ASC",nativeQuery=true)
	public List<RouteModel> viewAllRoute();
	
	Optional <RouteModel> findByFromCityAndToCity(String fromCity,String toCity);
	

    
}