package com.myapp.spring.web.api;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.myapp.spring.model.Search;
import com.myapp.spring.repository.SearchRepository;


@SpringBootTest

@AutoConfigureMockMvc
public class SearchAPITest {
	
	@MockBean
	private SearchRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	 @Test

		@DisplayName("Test find flight by Registered User With FromCity and ToCity and Date")
		public void testfindByRegisteredUserFromCityAndToCityAndDateOrderByCostAsc() throws Exception {
			
		    Date date1 = Date.valueOf("2021-01-01");
			Time depTime1 = Time.valueOf("12:00:00");
			Time arrTime1 = Time.valueOf("01:00:00");
		    Search flight1 = new Search(1,"AirIndia","Delhi","Mumbai",date1,depTime1,arrTime1,5000.00,50);
						
			Date date2 = Date.valueOf("2021-01-02");
			Time depTime2 = Time.valueOf("04:00:00");
			Time arrTime2 = Time.valueOf("05:00:00");
			Search flight2 = new Search(2,"Deccan","Chennai","Mumbai",date2,depTime2,arrTime2,7000.00,60);
						
			Date date3 = Date.valueOf("2021-01-03");
			Time depTime3 = Time.valueOf("07:00:00");
			Time arrTime3 = Time.valueOf("08:00:00");
			Search flight3 = new Search(3,"Spicejet","Kolkata","Delhi",date3,depTime3,arrTime3,3000.00,60);
			
			Date date4 = Date.valueOf("2021-01-01");
			Time depTime4 = Time.valueOf("03:00:00");
			Time arrTime4 = Time.valueOf("04:00:00");
			Search flight4 = new Search(4,"AirIndia-A1","Delhi","Mumbai",date4,depTime4,arrTime4,5050.00,55);
					
			Date date5 = Date.valueOf("2021-01-02");
			Time depTime5 = Time.valueOf("08:00:00");
			Time arrTime5 = Time.valueOf("09:00:00");
			Search flight5 = new Search(5,"Deccan-D1","Chennai","Mumbai",date5,depTime5,arrTime5,6900.00,60);
			
			Date date6 = Date.valueOf("2021-01-03");
			Time depTime6 = Time.valueOf("11:00:00");
			Time arrTime6 = Time.valueOf("12:00:00");
			Search flight6 = new Search(5,"Spicejet-S1","Kolkata","Delhi",date6,depTime6,arrTime6,3500.00,60);
	
			List<Search>flights = new ArrayList<>();
			flights.add(flight1);
			flights.add(flight2);
			flights.add(flight3);
			flights.add(flight4);
			flights.add(flight5);
			flights.add(flight6);
			
			String fromCity ="Chennai";
			String toCity ="Mumbai";
			Date date=Date.valueOf("2021-01-02");
			doReturn(Optional.of(flights)).when(repository).findByFromCityAndToCityAndDateOrderByCostAsc(flight5.getFromCity() ,flight5.getToCity(),flight5.getDate());
			
			mockMvc.perform(MockMvcRequestBuilders.get("/user/search/flight/{fromCity}/{toCity}/{date}",fromCity ,toCity,date))
			
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			
			.andExpect(jsonPath("$[4].flightNo" , is(5)))
			.andExpect(jsonPath("$[4].airlineName" , is("Deccan-D1")))
			.andExpect(jsonPath("$[4].fromCity" , is("Chennai")))
			.andExpect(jsonPath("$[4].toCity" , is("Mumbai")))
			.andExpect(jsonPath("$[4].date" , is("2021-01-02")))
			.andExpect(jsonPath("$[4].depTime" , is("08:00:00")))
			.andExpect(jsonPath("$[4].arrTime" , is("09:00:00")))
			.andExpect(jsonPath("$[4].cost" , is(6900.00)))
			.andExpect(jsonPath("$[4].avlSeats" , is(60)));
		}
	 
	 @Test

		@DisplayName("Test find flight by Un-Registered User With FromCity and ToCity and Date")
		public void testfindByUnregisteredUserFromCityAndToCityAndDateOrderByCostAsc() throws Exception {
			
		    Date date1 = Date.valueOf("2021-01-01");
			Time depTime1 = Time.valueOf("12:00:00");
			Time arrTime1 = Time.valueOf("01:00:00");
		    Search flight1 = new Search(1,"AirIndia","Delhi","Mumbai",date1,depTime1,arrTime1,5000.00,50);
						
			Date date2 = Date.valueOf("2021-01-02");
			Time depTime2 = Time.valueOf("04:00:00");
			Time arrTime2 = Time.valueOf("05:00:00");
			Search flight2 = new Search(2,"Deccan","Chennai","Mumbai",date2,depTime2,arrTime2,7000.00,60);
						
			Date date3 = Date.valueOf("2021-01-03");
			Time depTime3 = Time.valueOf("07:00:00");
			Time arrTime3 = Time.valueOf("08:00:00");
			Search flight3 = new Search(3,"Spicejet","Kolkata","Delhi",date3,depTime3,arrTime3,3000.00,60);
			
			Date date4 = Date.valueOf("2021-01-01");
			Time depTime4 = Time.valueOf("03:00:00");
			Time arrTime4 = Time.valueOf("04:00:00");
			Search flight4 = new Search(4,"AirIndia-A1","Delhi","Mumbai",date4,depTime4,arrTime4,5050.00,55);
					
			Date date5 = Date.valueOf("2021-01-02");
			Time depTime5 = Time.valueOf("08:00:00");
			Time arrTime5 = Time.valueOf("09:00:00");
			Search flight5 = new Search(5,"Deccan-D1","Chennai","Mumbai",date5,depTime5,arrTime5,6900.00,60);
			
			Date date6 = Date.valueOf("2021-01-03");
			Time depTime6 = Time.valueOf("11:00:00");
			Time arrTime6 = Time.valueOf("12:00:00");
			Search flight6 = new Search(5,"Spicejet-S1","Kolkata","Delhi",date6,depTime6,arrTime6,3500.00,60);
	
			List<Search>flights = new ArrayList<>();
			flights.add(flight1);
			flights.add(flight2);
			flights.add(flight3);
			flights.add(flight4);
			flights.add(flight5);
			flights.add(flight6);
			
			String fromCity ="Chennai";
			String toCity ="Mumbai";
			Date date=Date.valueOf("2021-01-02");
			doReturn(Optional.of(flights)).when(repository).findByFromCityAndToCityAndDateOrderByCostAsc(flight5.getFromCity() ,flight5.getToCity(),flight5.getDate());
			
			mockMvc.perform(MockMvcRequestBuilders.get("/search/flight/{fromCity}/{toCity}/{date}",fromCity ,toCity,date))
			
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			
			.andExpect(jsonPath("$[4].flightNo" , is(5)))
			.andExpect(jsonPath("$[4].airlineName" , is("Deccan-D1")))
			.andExpect(jsonPath("$[4].fromCity" , is("Chennai")))
			.andExpect(jsonPath("$[4].toCity" , is("Mumbai")))
			.andExpect(jsonPath("$[4].date" , is("2021-01-02")))
			.andExpect(jsonPath("$[4].depTime" , is("08:00:00")))
			.andExpect(jsonPath("$[4].arrTime" , is("09:00:00")))
			.andExpect(jsonPath("$[4].cost" , is(6900.00)))
			.andExpect(jsonPath("$[4].avlSeats" , is(60)));
		}
}
