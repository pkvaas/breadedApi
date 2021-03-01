package com.breaded.breadedapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.breaded.breadedapi.entity.Address;
import com.breaded.breadedapi.entity.BreadFilter;
import com.breaded.breadedapi.entity.Breads;
import com.breaded.breadedapi.entity.Subscription;
import com.breaded.breadedapi.entity.User;
import com.breaded.breadedapi.service.AddressService;
import com.breaded.breadedapi.service.BreadFilterService;
import com.breaded.breadedapi.service.BreadsService;
import com.breaded.breadedapi.service.SubscriptionService;
import com.breaded.breadedapi.service.UserService;

@CrossOrigin
@RestController(value = "api/v1/")
public class BreadedApiController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	BreadsService breadsService;
	
	@Autowired
	BreadFilterService breadFilterService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping("user")
	ResponseEntity<User> getUserByLoginCredentials(@RequestParam("email") String email, 
			@RequestParam("password") String password ){
		
		Optional<User> loginUser = userService.findByEmailAndPassword(email, password);
		
		 if (!loginUser.isPresent()) {
		        return new ResponseEntity<>(
		        		null, 
		          HttpStatus.NOT_FOUND);
		    }else {

		    return new ResponseEntity<>(
		    		loginUser.get(), 
		      HttpStatus.OK);
		    }
	}
	
	@PostMapping("user")
	ResponseEntity<User> signUp(@RequestBody User user){
		 return new ResponseEntity<>(
				 userService.Save(user), 
		      HttpStatus.OK);
	}
	
	@PostMapping("address")
	ResponseEntity<Address> addAddress(@RequestBody Address address){
		 return new ResponseEntity<>(
				 addressService.save(address), 
		      HttpStatus.OK);
	}
	
	
	@GetMapping("bread")
	ResponseEntity<List<Breads>> getBreads(){
		
		    return new ResponseEntity<>(
		    		breadsService.findAll(), 
		      HttpStatus.OK);
	}
	
	@PostMapping("bread")
	ResponseEntity<Breads> addBread(@RequestBody Breads bread){
		 return new ResponseEntity<>(
				 breadsService.save(bread), 
		      HttpStatus.OK);
	}
	
	@GetMapping("breadfilter")
	ResponseEntity<List<BreadFilter>> getBreadFilter(){
		
		    return new ResponseEntity<>(
		    		breadFilterService.findAll(), 
		      HttpStatus.OK);
	}
	
	@PostMapping("breadfilter")
	ResponseEntity<BreadFilter> addBreadFilter(@RequestBody BreadFilter breadFilter){
		 return new ResponseEntity<>(
				 breadFilterService.save(breadFilter), 
		      HttpStatus.OK);
	}
	
	@GetMapping("subscription")
	ResponseEntity<List<Subscription>> getSubscription(){
		
		    return new ResponseEntity<>(
		    		subscriptionService.findAll(), 
		      HttpStatus.OK);
	}
	
	@PostMapping("subscription")
	ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription){
		 return new ResponseEntity<>(
				 subscriptionService.save(subscription), 
		      HttpStatus.OK);
	}
}
