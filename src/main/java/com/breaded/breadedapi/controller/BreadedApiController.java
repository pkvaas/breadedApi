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
import org.springframework.web.bind.annotation.RestController;

import com.breaded.breadedapi.entity.Address;
import com.breaded.breadedapi.entity.BreadFilter;
import com.breaded.breadedapi.entity.Breads;
import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.OTP;
import com.breaded.breadedapi.entity.Subscription;
import com.breaded.breadedapi.entity.User;
import com.breaded.breadedapi.service.AddressService;
import com.breaded.breadedapi.service.BreadFilterService;
import com.breaded.breadedapi.service.BreadsService;
import com.breaded.breadedapi.service.MyBoxesService;
import com.breaded.breadedapi.service.SubscriptionService;
import com.breaded.breadedapi.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

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
	
	@Autowired
	MyBoxesService myBoxesService;


	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String SERVICE_ID = System.getenv("TWILIO_SERVICE_ID");
	
    @PostMapping("user/login")
	ResponseEntity<User> login(@RequestBody User user){
		
		Optional<User> loginUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		 if (loginUser.isPresent()) {
			 return new ResponseEntity<>(
		    		loginUser.get(), 
		      HttpStatus.OK);
		        
		}else {
	    	return new ResponseEntity<>(
	        		null, 
	        HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("user/signup")
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
	
	
	@PostMapping("myboxes")
	ResponseEntity<Myboxes> addMyBox(@RequestBody Myboxes myBoxes){
		 return new ResponseEntity<>(
				 myBoxesService.save(myBoxes), 
		      HttpStatus.OK);
	}
	
	@PostMapping("myboxes/findbyuser")
	ResponseEntity<List<Myboxes>> getMyboxesByUser(@RequestBody User user){
		
		    return new ResponseEntity<>(
		    		myBoxesService.findByUser(user), 
		      HttpStatus.OK);
	}
	
	
	@PostMapping("sms/sendotp")
	ResponseEntity<String> sendOTP(@RequestBody String phonenumber){
		
		 try {
			 
			 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				PhoneNumber number = PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(phonenumber))
						.setType("carrier").fetch();
				/*
				 * if(!number.getCountryCode().equals("GB")) { return new ResponseEntity<>(
				 * "Phone number not found.", HttpStatus.NOT_FOUND); }
				 */
		        
		    } catch(com.twilio.exception.ApiException e) {
		    	return new ResponseEntity<>(
						 "Phone number not found.", 
				      HttpStatus.NOT_FOUND);
		    }
		
		 Verification verification = Verification.creator(SERVICE_ID,
				 phonenumber,"sms").create();
        
        
		return new ResponseEntity<>(
				 "OTP Successfully sent", 
		      HttpStatus.OK);
	}
	
	@PostMapping("sms/verifyotp")
	ResponseEntity<String> verifyOTP(@RequestBody OTP otp){
		VerificationCheck verificationCheck;
		 try {
			 	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		        verificationCheck = VerificationCheck.creator(
		        		SERVICE_ID,
		        		otp.getOtp())
		            .setTo(otp.getPhonenumber()).create();
		        
		        if(!verificationCheck.getStatus().equals("approved")) {
		        	return new ResponseEntity<>(
							 "please enter valid OTP.", 
					      HttpStatus.NOT_FOUND);
		        }
		        
		    } catch(com.twilio.exception.ApiException e) {
		    	return new ResponseEntity<>(
						 "please enter valid OTP.", 
				      HttpStatus.NOT_FOUND);
		    }
        
		return new ResponseEntity<>(
				verificationCheck.getStatus(), 
		      HttpStatus.OK);
	}
	
	
    
}
