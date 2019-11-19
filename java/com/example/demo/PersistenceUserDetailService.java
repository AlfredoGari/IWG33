package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IUsuarioBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Usuario;

@Service
public class PersistenceUserDetailService implements UserDetailsService {

@Autowired
private PasswordEncoder pe;

@Autowired
private IUsuarioBusiness usuarioService;
private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		Usuario u=new Usuario();
		u.setUsername(username);
		u.setPassword(pe.encode("123"));
		u.setEnabled(true);
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		*/
		
		Usuario u;
		try {
			u = usuarioService.load(username);
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException();
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		return u;
	}

}
