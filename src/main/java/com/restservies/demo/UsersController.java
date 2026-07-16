package com.restservies.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
   
	// Api Call:- http://localhost:8080/users
	// Api Call for query:- http://localhost:8080/users/100?page=1&limit=50
	@GetMapping
	public String getUsers(@RequestParam(value = "page") int pageno,
			@RequestParam(value = "limit") int limitNo) {
		return "List of users Get Page Number:-"+pageno+" Limit:-"+limitNo;
	}
	
	// Api Call:- http://localhost:8080/users/100
	@GetMapping(path = "/{userId}")  //path parameter like userid is 100 then print and call this api
	public String getUsers(@PathVariable String userId) {
		return "List of users Get for userId:"+userId;
	}
	
	
	@PostMapping
	public String CreateUsers() {
		return "List of users Post";
	}
	
	@PutMapping
	public String UpdateUsers() {
		return "List of users Put";
	}
	
	@DeleteMapping
	public String deleteUsers() {
		return "List of users Delete";
	}
}
