package com.breaded.breadedapi.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.breaded.breadedapi.entity.Address;
import com.breaded.breadedapi.entity.BoxedBreads;
import com.breaded.breadedapi.entity.BreadFilter;
import com.breaded.breadedapi.entity.Breads;
import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.OTP;
import com.breaded.breadedapi.entity.Status;
import com.breaded.breadedapi.entity.Subscription;
import com.breaded.breadedapi.entity.User;
import com.breaded.breadedapi.service.AddressService;
import com.breaded.breadedapi.service.BreadFilterService;
import com.breaded.breadedapi.service.BreadsService;
import com.breaded.breadedapi.service.MyBoxesService;
import com.breaded.breadedapi.service.StatusService;
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
	
	@Autowired
	StatusService statusService;


	public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String SERVICE_ID = System.getenv("TWILIO_SERVICE_ID");
    
    public static final String URBIT_AUTH = System.getenv("URBIT_AUTH");
    public static final String URBIT_API_KEY = System.getenv("URBIT_API_KEY");
    public static final String URBIT_API_URI = System.getenv("URBIT_API_URI");
	
//    @GetMapping("user/login/{email}/{password}")
//	ResponseEntity<User> login(@RequestParam(value = "email") String email,
//		      @RequestParam(value = "password") String password){
//		
//		Optional<User> loginUser = userService.findByEmailAndPassword(email, password);
//		
//		 if (loginUser.isPresent()) {
//			 return new ResponseEntity<>(
//		    		loginUser.get(), 
//		      HttpStatus.OK);
//		        
//		}else {
//	    	return new ResponseEntity<>(
//	        		null, 
//	        HttpStatus.NOT_FOUND);
//		}
//	}
    
    @PostMapping("user/login")
	ResponseEntity<User> login(@RequestBody User user){
    	
    	List<User> userList = userService.findAll();
		 
		 Optional<User> login = userList.stream().filter(item ->
		 item.getEmail().equals(user.getEmail()) && item.getPassword().equals(user.getPassword())).findAny();
		 
		 if(login.isPresent()) {
				 return new ResponseEntity<>(		 
					 userService.Save(login.get()), 
				 
		      HttpStatus.OK);
		 }else {
	    	return new ResponseEntity<>(
	        		null, 
	        HttpStatus.NOT_FOUND);
		}
	}
    
    @PostMapping("user/findbyuser")
	ResponseEntity<User> findbyuser(@RequestBody User user){
		
    	List<User> userList = userService.findAll();
		 
		 Optional<User> login = userList.stream().filter(item -> item.getId() == user.getId()).findAny();
		if(login.isPresent()) {
			 return new ResponseEntity<>(		 
				 userService.Save(login.get()), 
			 
	      HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(
	       		null, 
	       HttpStatus.NOT_FOUND);
		}
	}
	
    @PutMapping("user")
	ResponseEntity<User> updateSubscription(@RequestBody User user){
		 return new ResponseEntity<>(
				 userService.Save(user), 
		      HttpStatus.OK);
	}
	
	
	@PostMapping("user/signup")
	ResponseEntity<User> signUp(@RequestBody User user){
		 return new ResponseEntity<>(
				 userService.Save(user), 
		      HttpStatus.OK);
	}
	
	@PostMapping("address")
	ResponseEntity<Address> addAddress(@RequestBody Address address){
		address.setUser(address.getUser());
		 return new ResponseEntity<>(
				 addressService.save(address), 
		      HttpStatus.OK);
	}
	
	@PutMapping("address")
	ResponseEntity<Address> editAddress(@RequestBody Address address){
		address.setUser(address.getUser());
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
	ResponseEntity<List<Breads>> addBread(@RequestBody List<Breads> breadList){
		 return new ResponseEntity<>(
				 breadsService.saveAll(breadList), 
		      HttpStatus.OK);
	}
	
	@PutMapping("bread")
	ResponseEntity<List<Breads>> editBread(@RequestBody List<Breads> breadList){
		 return new ResponseEntity<>(
				 breadsService.saveAll(breadList), 
		      HttpStatus.OK);
	}
	
	@GetMapping("breadfilter")
	ResponseEntity<List<BreadFilter>> getBreadFilter(){
		
		    return new ResponseEntity<>(
		    		breadFilterService.findAll(), 
		      HttpStatus.OK);
	}
	
	@PostMapping("breadfilter")
	ResponseEntity<List<BreadFilter>> addBreadFilter(@RequestBody List<BreadFilter> breadFilterList){
		 return new ResponseEntity<>(
				 breadFilterService.saveAll(breadFilterList), 
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
	
	@PutMapping("subscription")
	ResponseEntity<Subscription> editSubscription(@RequestBody Subscription subscription){
		 return new ResponseEntity<>(
				 subscriptionService.save(subscription), 
		      HttpStatus.OK);
	}
	
	@DeleteMapping("subscription")
	ResponseEntity<String> deleteSubscription(@RequestBody Subscription subscription){
		subscriptionService.delete(subscription);
		return new ResponseEntity<>(
				 "success", 
		      HttpStatus.OK);
	}
	
	@PostMapping("myboxes")
	ResponseEntity<Myboxes> addMyBox(@RequestBody Myboxes myBoxes){
		Set<BoxedBreads> boxedBreads = myBoxes.getBoxedBreadList();
		boxedBreads.forEach(boxedBread -> boxedBread.setMyboxes(myBoxes));
		myBoxes.setBoxedBreadList(boxedBreads);
		 return new ResponseEntity<>(
				 myBoxesService.save(myBoxes),
		      HttpStatus.OK);
	}
	
	@PutMapping("myboxes")
	ResponseEntity<Myboxes> updateMyBox(@RequestBody Myboxes myBoxes){
		Set<BoxedBreads> boxedBreads = myBoxes.getBoxedBreadList();
		boxedBreads.forEach(boxedBread -> boxedBread.setMyboxes(myBoxes));
		myBoxes.setBoxedBreadList(boxedBreads);
		 return new ResponseEntity<>(
				 myBoxesService.save(myBoxes),
		      HttpStatus.OK);
	}
	
	@PostMapping("myboxes/findbyuser")
	ResponseEntity<List<Myboxes>> getMyboxesByUser(@RequestBody User user){
		Calendar cal = Calendar.getInstance();
		
		String period = cal.get(Calendar.MONTH)+1+"-"+cal.get(Calendar.YEAR);
		    return new ResponseEntity<>(
		    		myBoxesService.findByUserAndPeriod(user, period), 
		      HttpStatus.OK);
	}
	
	@PostMapping("status")
	ResponseEntity<Status> addStatus(@RequestBody Status status){
		 return new ResponseEntity<>(
				 statusService.save(status), 
		      HttpStatus.OK);
	}
	
	@GetMapping("status")
	ResponseEntity<List<Status>> getStatus(){
		 return new ResponseEntity<>(
				 statusService.findAll(), 
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
	
	
	@PostMapping("urbit/createcart")
	ResponseEntity<String> createCart(@RequestBody String cart){
		
		RestTemplate restTemplate = new RestTemplate();
	
		ResponseEntity<String> response = restTemplate.exchange(URBIT_API_URI + "/v2/carts", HttpMethod.POST,
				getHttpEntity(cart), String.class);
		
		return response;
	}
	
	@PostMapping("urbit/checkouts")
	ResponseEntity<String> checkout(@RequestBody String cartReference){
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(URBIT_API_URI + "/v3/checkouts", HttpMethod.POST, 
				getHttpEntity(cartReference), String.class);
		
		return response;
	}
		
	@PutMapping("urbit/checkouts/{checkoutid}")
	ResponseEntity<String> setDeliveryInfo(@PathVariable("checkoutid") String checkoutid,@RequestBody String deliveryInfo){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange(URBIT_API_URI + "/v3/checkouts/"+ checkoutid +"/delivery", 
				HttpMethod.PUT, getHttpEntity(deliveryInfo), String.class);
		
		return response;
	}

	@DeleteMapping("urbit/checkouts/{checkoutid}")
	ResponseEntity<String> cancelCheckout(@PathVariable("checkoutid") String checkoutid){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange(URBIT_API_URI + "/v3/checkouts/"+ checkoutid, HttpMethod.DELETE,
				getHttpEntity(), String.class);
		
		return response;
	}
	
	
	private HttpEntity<?> getHttpEntity(String body){
		
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>(); 
		
		requestHeaders.add("Authorization", URBIT_AUTH);
		requestHeaders.add("X-API-Key", URBIT_API_KEY);
		requestHeaders.add("Content-Type", "application/json");
				
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);
		
		return httpEntity;
	
	}
	
	
	private HttpEntity<?> getHttpEntity(){
		
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>(); 
		
		requestHeaders.add("Authorization", URBIT_AUTH);
		requestHeaders.add("X-API-Key", URBIT_API_KEY);
				
		HttpEntity<?> httpEntity = new HttpEntity<Object>(null, requestHeaders);
		
		return httpEntity;
	
	}	
    
}
