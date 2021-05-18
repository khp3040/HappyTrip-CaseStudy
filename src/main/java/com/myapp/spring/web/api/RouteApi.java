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

import com.myapp.spring.model.RouteModel;
import com.myapp.spring.repository.RouteRepository;
import com.myapp.spring.service.RouteService;

@RestController
@RequestMapping("/admin/route")
public class RouteApi {
	@Autowired
	private RouteRepository repository;
	
	@Autowired
	private RouteService service;
	//Admin can add 1 new route
	//http://localhost:8888/admin/route
	@PostMapping
	public ResponseEntity<RouteModel> saveNewRoute(@RequestBody RouteModel route){

	return new ResponseEntity<RouteModel>(repository.save(route),HttpStatus.CREATED);
	
	}
	
	//Admin can add multiple new route
	//http://localhost:8888/admin/route/bulk
	@PostMapping("/bulk")
	public ResponseEntity<List<RouteModel>> saveListRoute(@RequestBody List<RouteModel> route){
	
		
	
	return new ResponseEntity<List<RouteModel>>(service.saveall(route),HttpStatus.CREATED);
	}
	

	//Admin Search all route displayed 
	//http://localhost:8888/admin/route
		@GetMapping
		public ResponseEntity<List<RouteModel>> viewall(){
			return new ResponseEntity<List<RouteModel>>(repository.viewAllRoute(),HttpStatus.OK);	
		}
	
	
	//Admin can Update route 
		//http://localhost:8888/admin/route/update
		@PutMapping("update")
		public ResponseEntity<RouteModel> updatecitybyid(
				@RequestBody RouteModel city){
		
		return new ResponseEntity<RouteModel>(service.updateCity(city),HttpStatus.CREATED);
		}
		
		
}
