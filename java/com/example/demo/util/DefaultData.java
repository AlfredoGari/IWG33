package com.example.demo.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.persistence.RolRepository;
import com.example.demo.persistence.UsuarioRepository;

@Service
public class DefaultData {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepository usuarioDAO;

	@Autowired
	private RolRepository rolDAO;

	public Usuario ensureUserAdmin() {
		List<Usuario> l = usuarioDAO.findByUsername("admin");
		if (l.size() == 0) {
			Usuario r = new Usuario();
			r.setUsername("admin");
			r.setFirstName("Administrador");
			r.setLastName("García");
			r.setEmail("admin@mail.com");
			r.setPassword(passwordEncoder.encode("password"));
			r.setEnabled(true);
			r.setAccountNonExpired(true);
			r.setAccountNonLocked(true);
			r.setCredentialsNonExpired(true);
			Set<Rol> roles=new HashSet<Rol>();
			roles.add(ensureRoleAdmin());
			roles.add(ensureRoleUser());
			r.setRoles(roles);
			return usuarioDAO.save(r);
		} else {
			return l.get(0);
		}
	}

	public Rol ensureRoleAdmin() {
		return ensureRol("ROLE_ADMIN");
	}

	public Rol ensureRoleUser() {
		return ensureRol("ROLE_USER");
	}

	private Rol ensureRol(String role) {
		List<Rol> l = rolDAO.findByRol(role);
		if (l.size() == 0) {
			Rol r = new Rol();
			r.setRol(role);
			return rolDAO.save(r);
		} else {
			return l.get(0);

		}
	}
}
