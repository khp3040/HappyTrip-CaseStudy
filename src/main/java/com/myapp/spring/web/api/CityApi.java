package com.myapp.spring.web.api; 

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;
import com.myapp.spring.service.CityService;

@RestController
@RequestMapping("/admin/cities")
public class CityApi {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;
	//Admin can add 1 new city
	//http://localhost:8888/admin/cities
	@PostMapping
	public ResponseEntity<City> saveNewCity(@RequestBody City city){

	return new ResponseEntity<City>(repository.save(city),HttpStatus.CREATED);
	
	}
	
	//Admin can add multiple new city
	//http://localhost:8888/admin/cities/bulk
	@PostMapping("/bulk")
	public ResponseEntity<List<City>> bulkProuctsInsert(@RequestBody List<City> city){
	
		
	
	return new ResponseEntity<List<City>>(service.saveall(city),HttpStatus.CREATED);
	}
	
	
	//Admin Search all cities displayed 
	//http://localhost:8888/admin/cities
	@GetMapping
	public ResponseEntity<List<City>> viewall(){
		return new ResponseEntity<List<City>>(repository.viewcity(),HttpStatus.OK);	
	}
	
	
	//Admin can find by city id
	//http://localhost:8888/admin/cities/findCity/id:{id}
	@GetMapping("findCity/id:{id}")
	public ResponseEntity<City> findByCityId(@PathVariable("id") String id){
		return new ResponseEntity<City>(repository.findBycityId(id).get(),HttpStatus.OK);
	}
	
	
	//Admin can find by city name
	//http://localhost:8888/admin/cities/findCity/city:{city}
	@GetMapping("findCity/city:{name}")
	public ResponseEntity<City> findByCityName(@PathVariable("name") String name){
		return new ResponseEntity<City>(repository.findBycityName(name).get(),HttpStatus.OK);
	}
	
	//Admin can Update City Name
		//http://localhost:8888/admin/cities/Update/city:{city}
		@PutMapping("update")
		public ResponseEntity<City> updatecitybyid(
				@RequestBody City city){
		
		return new ResponseEntity<City>(service.updateCity(city),HttpStatus.CREATED);
		}
		
		
}
