package com.myapp.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.City;

// Annotation is to identify that this is spring managed bean
// This is a data repository class
// 

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	//select * from product where price>
	
	@Query(value="SELECT * FROM Cities ORDER BY city_id ASC",nativeQuery=true)
	public List<City> viewcity();
	
	Optional <City> findBycityName(String cityName);
	
	Optional <City> findBycityId(String cityId);

    
}