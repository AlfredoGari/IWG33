package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@PropertySource({ "classpath:version.properties" })
@RestController
public class CoreRestController extends BaseRestController {

	@GetMapping(value = Constantes.URL_DENY)
	public ResponseEntity<String> deny() {
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping(value = Constantes.URL_LOGINOK)
	public ResponseEntity<String> loginok() {
		return new ResponseEntity<String>(userToJson(getUserLogged()).toString(), HttpStatus.OK);
	}

	@PostMapping(value = Constantes.URL_LOGINOK)
	public ResponseEntity<String> loginokpost() {
		return new ResponseEntity<String>(userToJson(getUserLogged()).toString(), HttpStatus.OK);
	}

	@GetMapping(value = Constantes.URL_AUTH_INFO)
	public ResponseEntity<String> authInfo() {
		
		return new ResponseEntity<String>(userToJson(getUserPrincipal()).toString(), HttpStatus.OK);
	}

	
	@Value("${version}")
	private String version;

	@GetMapping(value = Constantes.URL_VERSION)
	public ResponseEntity<String> getVersion() {
		return new ResponseEntity<String>(String.format("{\"version\":\"%s\"}", version), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = Constantes.URL_TOKEN)
	public ResponseEntity<Object> getToken(@RequestParam("username") String username,
			@RequestParam("diasvalido") int diasvalido) {
		return genToken(username, diasvalido);
	}

	

}
