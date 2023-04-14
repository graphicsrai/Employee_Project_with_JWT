package com.employee.controler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.AuthRequest;
import com.employee.security.JwtProvider;
@RestController
@RequestMapping("/app-auth")
public class AuthController {
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtProvider jwtProvider;

	    @GetMapping("/token")
	    public String getToken(@RequestBody AuthRequest authRequest) throws Exception {
	        // Get user details
	        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

	        if(userDetails.getPassword().equals(authRequest.getPassword())){
	            // Generate token
	            return jwtProvider.generateToken(authRequest.getUsername());
	        }

	        throw new Exception("User details invalid.");
	    }
}
