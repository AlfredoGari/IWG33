package com.example.demo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IUsuarioBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.JwtTokenUtil;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.JwtRequest;
import com.example.demo.model.dto.jwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationRestController {
	
	/*@Autowired
	private AuthenticationManager authenticationManager;*/
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUsuarioBusiness usuarioBusiness;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/pruebajwt")
	public ResponseEntity<jwtResponse> login(@RequestBody JwtRequest authenticationRequest){
		Usuario usuario = null;
		try{
			usuario = usuarioBusiness.load(authenticationRequest.getName());
		}catch (NotFoundException e) {
			return new ResponseEntity<jwtResponse>(HttpStatus.UNAUTHORIZED);
		}catch (BusinessException e) {
			return new ResponseEntity<jwtResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario != null) {
			boolean isValid = passwordEncoder.matches(
					authenticationRequest.getPassword(), 
					usuario.getPassword());
			if(!isValid)
				return new ResponseEntity<jwtResponse>(HttpStatus.UNAUTHORIZED);
		}
		
		final String token = jwtTokenUtil.generateToken(authenticationRequest.getName());
		jwtResponse jwtresponses = new jwtResponse();
		jwtresponses.setUsuario(usuario.getUsername());
		jwtresponses.setToken(token);
		return new ResponseEntity<jwtResponse>(jwtresponses,HttpStatus.OK);
	}
	
	
}
